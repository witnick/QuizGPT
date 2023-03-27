'''
Receiving message format:
- number: number of questions to generate
- text: text to generate questions from
- id: unique identifier for quiz

Sending message format:
- id: unique identifier for quiz
- number: number of questions generated
- results: list of question-answer pairs
'''
import os
import json
import pika
import requests
from flask import Flask, request
import threading

app = Flask(__name__)

# RabbitMQ configurations
RABBITMQ_HOST = os.environ.get("RABBITMQ_HOST", "localhost")
INPUT_QUEUE = os.environ.get("INPUT_QUEUE", "input_queue")
OUTPUT_QUEUE = os.environ.get("OUTPUT_QUEUE", "output_queue")

# ChatGPT API configurations
CHATGPT_API_KEY = os.environ.get("OPENAI_SECRET_KEY")
CHATGPT_API_ENDPOINT = os.environ.get("CHATGPT_API_ENDPOINT", "https://api.openai.com/v1/chat/completions")
# https://api.openai.com/v1/engines/davinci-codex/completions
MOCK_RESPONSE = '''Question 1: What is a binary search tree?
Answer: A binary search tree is a binary tree data structure where each node has at most two child nodes and the value of each node is greater than or equal to the values in its left subtree and less than or equal to the values in its right subtree.

Question 2: What is the time complexity of searching for an element in a binary search tree?
Answer: The time complexity of searching for an element in a binary search tree is O(log n) on average, where n is the number of nodes in the tree.

Question 3: What is the difference between a stack and a queue?
Answer: A stack is a data structure that follows the Last-In-First-Out (LIFO) principle, where the last element added to the stack is the first one to be removed. A queue, on the other hand, follows the First-In-First-Out (FIFO) principle, where the first element added to the queue is the first one to be removed.'''

headers = {
    "Content-Type": "application/json",
    "Authorization": f"Bearer {CHATGPT_API_KEY}"
}


def chatgpt_request(prompt, nb_questions, max_tokens=200):
    final_prompt = f"Generate a quiz of {nb_questions} questions from the following prompt:\n{prompt}\n\
        Also generate answers for each of the questions generated from the prompt. Let the formatting be 'Question 1: ...'\
              then a new line, and then with its answer as 'Answer: ...' for each of the questions. Each block of question and answer is separated with 2 new lines."
    data = {
        "model": "gpt-3.5-turbo-0301",
        "messages": [
            {"role": "user", "content": final_prompt}
        ],
        "max_tokens": max_tokens,
        "n": 1,
        "temperature": 0.7,
    }

    #response = requests.post(CHATGPT_API_ENDPOINT, headers=headers, json=data)
    #print("Response: ", response.json())
    #response.raise_for_status()
    #return response.json()["choices"][0]["message"]["content"].strip()
    return MOCK_RESPONSE

def rabbitmq_connection():
    print("Starting RabbitMQ connection.")
    connection = pika.BlockingConnection(pika.ConnectionParameters(host=RABBITMQ_HOST))
    if connection.is_open:
        print(f"Successfully connected to RabbitMQ at {RABBITMQ_HOST}")
    else:
        print(f"Failed to connect to RabbitMQ at {RABBITMQ_HOST}")
        return None
    print("Creating channel")
    channel = connection.channel()
    print("Creating queues")
    channel.queue_declare(queue=INPUT_QUEUE)
    channel.queue_declare(queue=OUTPUT_QUEUE)
    return channel

def parse_qa_pairs(text):
    questions = text.split("\n\n")
    formatted_questions = []

    for question in questions:
        split_question = question.split("\n")
        question_text = split_question[0].strip()
        answer = split_question[1].strip()

        formatted_questions.append({"question": question_text, "answer": answer})
    return formatted_questions

def callback(ch, method, properties, body):
    message = json.loads(body)
    quiz_id = message["id"]
    number = message["number"]
    text = message["text"]

    generated_text = chatgpt_request(text, number)
    print('Generated text---\n',generated_text)
    # Parse questions and answers
    formatted_qa = parse_qa_pairs(generated_text)

    # Add message back to the queue
    response_message = {
        "id": quiz_id,
        "number": number,
        "results": formatted_qa
    }
    print('Response message---\n',response_message)

    ch.basic_publish(exchange="", routing_key=OUTPUT_QUEUE, body=json.dumps(response_message))
    ch.basic_ack(delivery_tag=method.delivery_tag)
    print('Finished callback')

def start_consuming():
    channel = rabbitmq_connection()
    if channel is None:
        print("Error connecting to input queue. Exiting...")
        return
    else: print('RabbitMQ connection established and channel created. Starting consuming...')
    channel.basic_qos(prefetch_count=1)
    channel.basic_consume(queue=INPUT_QUEUE, on_message_callback=callback)
    channel.start_consuming()

@app.route("/test")
def test():
    test_prompt = 'Algorithm and data structures. Medium difficulty.'
    response = chatgpt_request(test_prompt, 2)
    print(response)
    finalr = parse_qa_pairs(response)
    print(finalr)
    return finalr

@app.route("/")
def home():
    return "Hello World!"

# @app.route("/start")
# def start():
#     channel = rabbitmq_connection()
#     if channel is None:
#         print("Error connecting to input queue. Exiting...")
#         return
#     channel.basic_qos(prefetch_count=1)
#     channel.basic_consume(queue=INPUT_QUEUE, on_message_callback=callback)
#     channel.start_consuming()

    return {"status": "success"}

if __name__ == "__main__":
    rabbitmq_thread = threading.Thread(target=start_consuming)
    rabbitmq_thread.start()
    app.run(host="0.0.0.0", port=5000)
