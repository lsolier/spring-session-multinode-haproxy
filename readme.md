# Spring Session with HAProxy
## _ Demonstrate how to share session using spring boot and HAProxy_

Create docker image from webapp.

```sh
./mvnw compile jib:dockerBuild
```

Start all the services with docker-compose:

```sh
cd docker
docker-compose up
```

In a browser, go to http://localhost/greeting