# Startup Procedure

## 1. Start backend

```
    cd Mono/
    mvn clean verify -fae
    mvn spring-boot:run

    cd Deck/
    mvn clean verify -fae
    mvn spring-boot:run
```

Command line start on selected port:

```
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8005
```


### 1.3 Async

Start
```
    docker run -d --name=kafka -p 9092:9092 apache/kafka
```
Stop
```
    docker stop $(docker ps -a -q)
```

## 2. Start frontend

```
    cd frontend/
    npm install
    npm run dev
```

## 3. Running

```
    http://localhost:5173/
    http://localhost:8000/swagger-ui/index.html
```

##  4. Checks of various types

Database:
```
    http://localhost:8001/h2-console
```

## Various

```
docker exec -it kafka \
  /opt/kafka/bin/kafka-console-consumer.sh \
  --bootstrap-server localhost:9092 \
  --topic round-test \
  --from-beginning
```