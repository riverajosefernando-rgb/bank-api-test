FROM gradle:8.7-jdk17

WORKDIR /app

COPY . .

RUN gradle clean build -x test

CMD ["gradle", "test"]