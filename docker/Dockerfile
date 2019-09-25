FROM openjdk:8-jre-alpine
MAINTAINER "Sounak"
RUN mkdir -p /var/data/
COPY counter.txt /var/vata/counter.txt
COPY counter-0.0.1-SNAPSHOT.jar .
CMD java -jar counter-0.0.1-SNAPSHOT.jar
