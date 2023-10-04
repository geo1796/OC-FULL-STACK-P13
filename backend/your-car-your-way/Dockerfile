FROM openjdk:17
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
COPY src/main ./src/main
CMD ["./mvnw", "spring-boot:run"]