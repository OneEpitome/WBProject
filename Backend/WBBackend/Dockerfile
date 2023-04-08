FROM gradle

WORKDIR /whereball
COPY . .
RUN ./gradlew clean build -x test && cp build/libs/*.jar ./app.jar

CMD ["java", "-jar", "app.jar"]
