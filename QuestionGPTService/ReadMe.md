# How to Run

[Quick flask guide](https://flask.palletsprojects.com/en/2.2.x/quickstart/)

To run the application, use the flask command or python -m flask. You need to tell the Flask where your application is with the --app option.

```
$ flask --app app.py run
 * Serving Flask app 'hello'
 * Running on http://127.0.0.1:5000 (Press CTRL+C to quit)
```

- Alternatively: `py -m flask --app app.py run`
- To run in debug mode (automatic changes): `py -m flask --app app run --debug`
- Run RabbitMQ locally: `docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management`

RabbitMQ website:

- http://localhost:15672/
- user pass are both `guest`
