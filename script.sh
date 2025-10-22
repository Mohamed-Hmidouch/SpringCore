#!/bin/bash

# ---
# Script pour créer l'arborescence du projet Spring Core API.
# Exécutez ce script depuis votre dossier `~/spring_core`.
# ---

echo "Vérification et création de la structure du projet..."

# 1. Création des répertoires de base pour le code source et les tests
# L'option -p crée les répertoires parents s'ils n'existent pas.
mkdir -p src/main/java/com/example/config
mkdir -p src/main/java/com/example/model
mkdir -p src/main/java/com/example/repository
mkdir -p src/main/java/com/example/service
mkdir -p src/main/java/com/example/controller
mkdir -p src/test/java/com/example
mkdir -p src/main/webapp/static
mkdir -p target

# 2. Création des fichiers vides avec la commande 'touch'
# 'touch' ne modifiera pas les fichiers s'ils existent déjà.

# Fichiers à la racine
touch Dockerfile
touch docker-compose.yml

# Fichiers de configuration Java
touch src/main/java/com/example/config/PersistenceConfig.java
touch src/main/java/com/example/config/WebConfig.java
touch src/main/java/com/example/config/WebAppInitializer.java
touch src/main/java/com/example/config/AppProfiles.java

# Fichiers de l'application (modèle, repository, etc.)
touch src/main/java/com/example/model/User.java
touch src/main/java/com/example/repository/UserRepository.java
touch src/main/java/com/example/service/UserService.java
touch src/main/java/com/example/controller/UserController.java

# Fichiers de ressources
touch src/main/resources/application-dev.properties
touch src/main/resources/application-prod.properties
touch src/main/resources/log4j2.xml

# Fichier de test
touch src/test/java/com/example/UserControllerTest.java

# Fichier .war factice dans le répertoire target
touch target/spring-core-api.war

echo "Arborescence créée avec succès !"

# Affiche l'arborescence finale pour vérification
tree