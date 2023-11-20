FROM eclipse-temurin:17-jdk-focal

WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN ./mvnw dependency:go-offline

COPY ./src ./src
RUN ./mvnw package -DskipTests 
RUN ls -la ./target
RUN find ./target -type f -name '*.jar' -exec cp {} /opt/app/app.jar \; -quit
RUN ls -la /opt/app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/app.jar" ]