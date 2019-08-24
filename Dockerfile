FROM openjdk:8-jdk-alpine

RUN mkdir /app
WORKDIR /app

COPY ./sampleservice-controller/target/sampleservice-controller-1.0.0.jar /app/app.jar


RUN chgrp -R 0 /app \
&& chmod -R g=u /app

ENV JAVA_OPTS=""
EXPOSE 8080
ENTRYPOINT exec java -jar /app/app.jar --debug
