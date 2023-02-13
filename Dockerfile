FROM openjdk:17-bullseye

WORKDIR /app

COPY /target/*.jar /srv/
COPY /src/main/resources/application.yml /srv/config/

EXPOSE 8080

ENTRYPOINT ["java","-jar","/srv/smart-0.0.1-SNAPSHOT.jar", "--spring.config.location=file:/srv/config/application.yml"]