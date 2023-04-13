# FINAL DEPLOYED VERSION is in this repository: https://github.com/Fryingpannn/QuizGPTService
[Quick flask guide](https://flask.palletsprojects.com/en/2.2.x/quickstart/)

# Prerequisites

- Sign up for [OpenAI](https://platform.openai.com/account/api-keys) and get your secret key. This is rotated periodically so needs to be updated.
- Recommended to instead use a Python virtual environment. Download the following dependencies with `requirements.txt` instead. Else:
- Install Python
- Install Flask `pip install Flask`
- Install RabbitMQ `pip install pika`
- Install OpenAI `pip install requests`
- Install dotenv to enable Environment Variables `pip install python-dotenv`

# How to Run

To run the application, use the flask command or python -m flask. You need to tell the Flask where your application is with the --app option.

0. Create `.env` file (ask Pan for credentials) ([OpenAI console](https://platform.openai.com/account/api-keys)):

```
OPENAI_SECRET_KEY="Get your own from the OpenAI console"
RABBITMQ_HOSTNAME='Ask Pan'
RABBITMQ_USER='Ask Pan'
RABBITMQ_PORT='5672'
RABBITMQ_PASSWORD='Ask Pan'
RABBITMQ_PREFIX='amqps://'
ENVIRONMENT='prod'
SENDER="Ask Pan"
```

1. Run RabbitMQ locally: `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management`
2. `flask --app app.py run` (`py -m flask --app app.py run`)
   - To run in debug mode (automatic changes): `py -m flask --app app run --debug`
3. Go to root of localhost site to start the RabbitMQ connection (http://127.0.0.1:5000)

RabbitMQ dashboard to manage messages:

- http://localhost:15672/
- user pass are both `guest`

---

# Deployment

## Python Flask App

- Python app deployed with Vercel at https://quizgpt-service.vercel.app/
- For this to work, need the following versions installed:

```
Flask==1.1.4
MarkupSafe==2.0.1
```

- Dashboard: https://vercel.com/fryingpannn/quizgpt-service
- To redeploy, push changes to the repo or `vercel --prod`.

------- PYTHONANYWHERE DOESN'T WORK WITH NON-PUBLIC APIs -> Use the Vercel deployed URL.

- The Python app is deployed using PythonAnywhere at http://fryingpannn.pythonanywhere.com/
  - https://www.pythonanywhere.com/user/fryingpannn/webapps/#tab_id_fryingpannn_pythonanywhere_com
  - Files must be changed directly from within the dashboard source code.
  - Use bash console on the dashboard to install new libraries.

## RabbitMQ

- The RabbitMQ service is deployed using CloudAMQP at https://api.cloudamqp.com/console/eabc96f0-a3bb-456b-b3f2-04b798d7e228/details. Messages inside can be managed from the console.
- Ask Pan for the URL of this service.

### RabbitMQ message formats:

Receiving message format:

- number: number of questions to generate
- text: text to generate questions from
- id: unique identifier for quiz
- sender: has to be an allowed sender

Sending message format:

- id: unique identifier for quiz
- number: number of questions generated
- results: list of question-answer pairs
- sender: has to be an allowed sender

E.g.:

```
{
    "id": 1,
    "number": 3,
    "text": "Data structures and algorithms. Medium difficulty.",
    "sender": SENDER from env file
}
{
    "id": 2,
    "number": 3,
    "text": "Vegetables and fruits. Medium difficulty.",
    "sender": SENDER from env file
}
```
