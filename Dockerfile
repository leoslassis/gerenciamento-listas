# ===== stage de build =====
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app

# Copia o wrapper e o pom primeiro pra melhor cache
COPY mvnw mvnw
COPY .mvn .mvn
COPY pom.xml pom.xml

# Faz o download do cache de dependências
RUN chmod +x mvnw && ./mvnw -q -B -DskipTests dependency:go-offline

# Copia o restante do código e builda
COPY src src
RUN ./mvnw -q -B clean package -DskipTests

# ===== stage de runtime =====
FROM eclipse-temurin:21-jre
WORKDIR /app

# Copia o jar gerado (ajuste o nome se necessário)
COPY --from=build /app/target/servico-listas-*.jar app.jar

# Porta padrão do serviço
EXPOSE 8080

# Parâmetros de JVM opcionais (GC + container awareness)
ENTRYPOINT ["java","-XX:+UseZGC","-XX:+ExitOnOutOfMemoryError","-jar","app.jar"]
