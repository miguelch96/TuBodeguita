#FROM maven:3.5-jdk-8-alpine as build 
#WORKDIR /app
#COPY . /app 
#RUN mvn -DTuBodeguitaTest.skip.tests=true -DTuBodeguitaTest.failure.ignore=true clean install

#FROM openjdk:8-jre-alpine
#WORKDIR /app
#COPY /TuBodeguitaWeb/target/TuBodeguitaWeb-2.0.jar /app
#EXPOSE 80
#CMD ["java", "-jar", "TuBodeguitaWeb-2.0.jar"]

FROM tomcat:8.0-jre8
WORKDIR /usr/local/tomee/
ADD tomcat/tomcat-users.xml conf/
ADD tomcat/web.xml conf/
# copy and deploy application
WORKDIR /usr/local/tomee/webapps/
COPY /TuBodeguitaWeb/target/TuBodeguitaWeb-2.0.war TuBodeguitaWeb-2.0.war
# start tomcat7 
EXPOSE 8080
CMD ["catalina.sh", "run"]
