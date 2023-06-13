FROM openjdk:11
COPY target/*.jar kcmainmicro-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","kcmainmicro-0.0.1-SNAPSHOT.jar"] CMD ["-start"]