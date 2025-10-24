.PHONY: default
default:
	mvn clean package -DskipTests
	docker cp target/ROOT.war spring_tomcat:/usr/local/tomcat/webapps/
	docker restart spring_tomcat
