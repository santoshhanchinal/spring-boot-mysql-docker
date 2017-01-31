FROM java:8
VOLUME /tmp
ADD demo.war demo.war
RUN bash -c 'touch /demo.war'
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-war","/demo.war"]