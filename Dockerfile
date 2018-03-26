FROM java:8-jre
ADD target/webui-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","./webui-0.0.1-SNAPSHOT.jar", "--port=8080"]