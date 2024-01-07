# FROM eclipse-temurin:17-alpine
# VOLUME /tmp
# ADD build/libs/spring-testing*.jar app.jar
# ADD .env .env
# ENTRYPOINT [ "sh", "-c", "source .env && java -jar app.jar" ]
# FROM tomcat:9-jre8-alpine
# COPY app.jar /etc/local/tomcat/webapps
#
# Build stage

FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# Package stage
FROM tomcat:10.1-jdk17-openjdk-slim
COPY --from=build /home/app/target/clinica-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/clinica.war
EXPOSE 8080
