FROM maven:3.5-jdk-8-alpine as build 
WORKDIR /app
COPY . /app 
RUN mvn -DTuBodeguitaTest.skip.tests=true -DTuBodeguitaTest.failure.ignore=true clean install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/TuBodeguitaWeb/target/TuBodeguitaWeb-2.0.war /app
EXPOSE 80
CMD ["java -war /app/TuBodeguitaWeb/target/TuBodeguitaWeb-2.0.war"]