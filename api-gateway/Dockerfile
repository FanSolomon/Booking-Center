FROM java:8
#VOLUME /tmp
ADD target/api-gateway-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]
EXPOSE 5555