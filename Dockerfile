# Set java 8 base image
FROM java:8

# File Author / Maintainer
MAINTAINER Arnaud Zdziobeck <laloutre42@gmail.com>

VOLUME /tmp

ADD target/enigme30*.war app.war
CMD bash -c 'touch /app.war'
ENTRYPOINT ["/app.war", "--spring.profiles.active=prod"]
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]