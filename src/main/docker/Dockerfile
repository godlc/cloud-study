FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD cloud-study-1.0.jar app.jar #复制文件并且重命名。
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]

