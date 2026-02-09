# -------- Stage 1: build --------
FROM maven:3.9.9-eclipse-temurin-21 AS build

WORKDIR /app

# Copia primeiro o pom para aproveitar cache de dependências
COPY pom.xml .
RUN mvn -B -q -e -DskipTests dependency:go-offline

# Copia código e faz build
COPY src ./src
RUN mvn -B -DskipTests package

# -------- Stage 2: runtime --------
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia jar gerado
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]
