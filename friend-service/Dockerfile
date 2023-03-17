FROM openjdk:17-oracle
VOLUME /tmp
ADD target/*.jar app.jar
ENV JAVA_OPTS=""
ENTRYPOINT ["java", "-jar", "/app.jar"]