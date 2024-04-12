FROM eclipse-temurin:17-jdk-focal as builder
WORKDIR /opt/app
ARG PROJECT_ROOT="."
COPY ${PROJECT_ROOT}/.mvn/ .mvn
COPY ${PROJECT_ROOT}/mvnw ${PROJECT_ROOT}/pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline
COPY ${PROJECT_ROOT}/src ./src
RUN ./mvnw clean install -DskipTests 
RUN find ./target -type f -name '*.jar' -exec cp {} /opt/app/app.jar \; -quit

FROM eclipse-temurin:17-jre-alpine
COPY --from=builder /opt/app/*.jar /opt/app/
RUN ls -la /opt/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar" ]