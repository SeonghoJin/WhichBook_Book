FROM openjdk:11.0.8-slim-buster
COPY . /usr/src/book_service
WORKDIR /usr/src/book_service
CMD ["./gradlew", "bootRun"]