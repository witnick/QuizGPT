[Quick flask guide](https://flask.palletsprojects.com/en/2.2.x/quickstart/)

# Prerequisites

- Install Python
- Install Flask `pip install Flask`
- Install RabbitMQ `pip install pika`
- Install dotenv to enable Environment Variables `pip install python-dotenv`

# How to Run

To run the application, use the flask command or python -m flask. You need to tell the Flask where your application is with the --app option.

1. Run RabbitMQ locally: `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management`
2. `flask --app app.py run` (`py -m flask --app app.py run`)
   - To run in debug mode (automatic changes): `py -m flask --app app run --debug`
3. Go to root of localhost site to start the RabbitMQ connection (http://127.0.0.1:5000)

RabbitMQ dashboard to manage messages:

- http://localhost:15672/
- user pass are both `guest`
