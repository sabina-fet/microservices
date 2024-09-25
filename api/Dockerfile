FROM eclipse-temurin:17-jdk-jammy as builder
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN chmod +x mvnw
RUN apt-get update && apt-get -y install dos2unix && dos2unix mvnw
RUN ./mvnw dependency:go-offline
COPY ./src ./src
RUN ./mvnw clean install
 
 
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar /app/*.jar
ENTRYPOINT ["java", "-jar", "/app/*.jar" ]
