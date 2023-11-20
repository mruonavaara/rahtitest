FROM eclipse-temurin:17-jdk-focal as builder

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install -DskipTests 
RUN ls -la ./target
RUN find ./target -type f -name '*.jar' -exec cp {} /opt/app/app.jar \; -quit
RUN ls -la /opt/app

FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /opt/app/*.jar /opt/app/
RUN ls -la /opt/app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar" ]