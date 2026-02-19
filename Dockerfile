# --- ETAPA 1: BUILD (Maven) ---
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
# Compilamos saltando tests (para evitar errores de conexión a DB durante el build)
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUNTIME (Tomcat 10 para Jakarta EE) ---
FROM tomcat:10.1-jdk21

RUN apt-get update && apt-get install -y unzip && rm -rf /var/lib/apt/lists/*
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copiamos el WAR
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/ROOT.war

# Copiamos el script
COPY entrypoint.sh /entrypoint.sh

# --- LA CURA PARA WINDOWS ---
# Esto elimina los saltos de línea \r que causan errores en Linux
RUN sed -i 's/\r$//' /entrypoint.sh
# -----------------------------

RUN chmod +x /entrypoint.sh

EXPOSE 8080
CMD ["/entrypoint.sh"]