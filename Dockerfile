# Etapa 1: Construir o projeto com Gradle
FROM gradle:7.6.0-jdk17 AS build
WORKDIR /app
COPY --chown=gradle:gradle . .

# Executa o build com o Gradle, desabilitando testes
RUN gradle build -x test --no-daemon

# Etapa 2: Rodar a aplicação
FROM openjdk:17-jdk-alpine
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=build /app/build/libs/*.jar app.jar

# Comando para rodar a aplicação
CMD ["java", "-jar", "app.jar"]

# Expor a porta onde a API irá rodar
EXPOSE 8080
