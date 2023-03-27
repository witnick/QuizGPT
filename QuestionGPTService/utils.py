MOCK_RESPONSE = '''Question 1: What is a binary search tree?
Answer: A binary search tree is a binary tree data structure where each node has at most two child nodes and the value of each node is greater than or equal to the values in its left subtree and less than or equal to the values in its right subtree.

Question 2: What is the time complexity of searching for an element in a binary search tree?
Answer: The time complexity of searching for an element in a binary search tree is O(log n) on average, where n is the number of nodes in the tree.

Question 3: What is the difference between a stack and a queue?
Answer: A stack is a data structure that follows the Last-In-First-Out (LIFO) principle, where the last element added to the stack is the first one to be removed. A queue, on the other hand, follows the First-In-First-Out (FIFO) principle, where the first element added to the queue is the first one to be removed.'''

def gpt_prompt(prompt, nb_questions):
    return f"Generate a quiz of {nb_questions} questions from the following prompt:\n{prompt}\n\
        Also generate answers for each of the questions generated from the prompt. Let the formatting be 'Question 1: ...'\
              then a new line, and then with its answer as 'Answer: ...' for each of the questions. Each block of question and answer is separated with 2 new lines."