FROM openjdk

WORKDIR /whereball
RUN mkdir -p src/main/resources/static/images
COPY ./build/libs .

CMD ["java", "-jar", "WBBackend-0.0.1-SNAPSHOT.jar"]
