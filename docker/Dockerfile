# Étape 1 : Utiliser une image de base Maven pour compiler le projet
FROM maven:3.8.5-openjdk-17 AS build

# Définit le répertoire de travail
WORKDIR /app
# Copie tout le projet (les fichiers sources)
COPY . .
# Étape 2 : Utiliser une image légère JDK pour exécuter l'application
FROM openjdk:17-jdk-alpine
# Définit le répertoire de travail pour l'application
WORKDIR /app
# Copie le fichier JAR compilé depuis l'étape de construction
COPY --from=build /app/target/spring-boot-jpa-h2-0.0.1-SNAPSHOT.jar /app/app.jar

# Expose le port utilisé par l'application (8081 dans votre configuration)
EXPOSE 8081

# Commande pour exécuter le JAR
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
