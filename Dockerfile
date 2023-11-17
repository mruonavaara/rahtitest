FROM eclipse-temurin:17-jdk-focal
WORKDIR /opt/app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x ./mvnw
RUN echo $JAVA_HOME
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw -DskipTests clean install
RUN ls -la ./target
RUN cp ./target/*.jar /opt/app/
RUN ls -la /opt/app

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/opt/app/*.jar" ]