FROM amazoncorretto:17
EXPOSE 8080
VOLUME /tmp
ARG JAR_FILE=target/best-matched-restaurants-bf-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
COPY /src/main/resources/data/restaurants.csv /app/data/restaurants.csv
COPY /src/main/resources/data/cuisines.csv /app/data/cuisines.csv
ENTRYPOINT ["java","-jar","/app.jar"]
