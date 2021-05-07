FROM adoptopenjdk/openjdk11:jdk-11.0.5_10-alpine
WORKDIR /src
EXPOSE 8080
EXPOSE 5005
ENTRYPOINT ["java","-jar", "-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005","registration-system-1.0-SNAPSHOT.jar"]
