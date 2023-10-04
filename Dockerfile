# Stage 1: Build shared_module
FROM maven:latest as shared-builder

WORKDIR /app

COPY pom.xml .
RUN mvn clean install -N

COPY shared_module/pom.xml shared_module/pom.xml
COPY shared_module/src shared_module/src


# Bauen und Installieren des shared_module
RUN mvn -f shared_module/pom.xml clean install

# Stage 2: Build jdbc_module
FROM maven:latest as jdbc-builder

WORKDIR /app

# Kopieren des gesamten lokalen Maven-Repositories
COPY --from=shared-builder /root/.m2 /root/.m2

COPY jdbc_module/pom.xml jdbc_module/pom.xml
COPY jdbc_module/src jdbc_module/src

# Bauen des jdbc_module
RUN mvn -f jdbc_module/pom.xml clean package

# Stage 3: Erstellen des finalen Images
FROM eclipse-temurin:17-jre

WORKDIR /app

# Kopieren der jdbc_module JAR aus dem Builder-Image
COPY --from=jdbc-builder /app/jdbc_module/target/jdbc_module-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "jdbc_module-0.0.1-SNAPSHOT.jar"]
