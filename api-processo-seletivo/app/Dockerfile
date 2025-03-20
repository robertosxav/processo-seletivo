# Usa a imagem oficial do OpenJDK
FROM amazoncorretto:17

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo JAR gerado pelo Maven
COPY target/app-0.0.1-SNAPSHOT.jar /app/app-0.0.1-SNAPSHOT.jar

# Expõe a porta 8080 para acesso externo
EXPOSE 8080

# Comando para rodar a aplicação
CMD ["java", "-jar", "/app/app-0.0.1-SNAPSHOT.jar"]
