FROM openjdk:11
MAINTAINER Amit Sharma<sharma.ami.07@gmail.com>
VOLUME /tmp
ARG JAR_FILE=target/candyshop-0.0.1-SNAPSHOT.war
ADD ${JAR_FILE} candyshopapp.war
ENTRYPOINT ["java", "-jar", "/candyshopapp.war"]
