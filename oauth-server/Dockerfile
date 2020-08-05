FROM java:8
#VOLUME /tmp
ADD target/oauth-server-1.0.0-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","app.jar","--spring.profiles.active=prod"]
EXPOSE 8090