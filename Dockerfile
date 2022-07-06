FROM openjdk:11
MAINTAINER Amit Sharma<sharma.ami.07@gmail.com>
VOLUME /tmp
ARG JAR_FILE=target/candyshop-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} candyshopapp.jar
ENTRYPOINT ["java", "-jar", "/candyshopapp.jar"]
