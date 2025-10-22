# Dockerfile stable - Build Maven local requis
# Exécutez d'abord: mvn clean package -DskipTests

FROM tomcat:11.0-jdk17

# Supprimer l'application ROOT par défaut de Tomcat
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Copier le fichier WAR construit localement
COPY target/ROOT.war /usr/local/tomcat/webapps/ROOT.war

# Exposer le port Tomcat
EXPOSE 8080

# Démarrer Tomcat
CMD ["catalina.sh", "run"]