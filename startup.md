# Startup Procedure

## 1. Start infrastructure (DBs, kafka)
```
 docker compose up -d
```

## 2. Backend

### 2.1 Make sure images exist

#### 2.1.a
```
 mvn clean package
 docker build -t deck:0.0.1 . 
 docker build -t [container_name] .
```

#### 2.1.b
```
 mvn spring-boot:build-image
```
OR
```
 .\mvnw.cmd spring-boot:build-image    
```

### 2.2 Run images

```
 docker run -d --name deck --network m2026 -p 8001:8001 -e SPRING_PROFILES_ACTIVE=docker deck:0.0.1
  docker run -d --name round --network m2026 -p 8000:8000 -e SPRING_PROFILES_ACTIVE=docker round:0.0.1-SNAPSHOT
  docker run -d --name resolver --network m2026 -p 8006:8006 -e SPRING_PROFILES_ACTIVE=docker resolver:0.0.1-SNAPSHOT
```

## Frontend

```
    cd frontend/
    npm install
    
    docker build -t frontend:0.1 .
    docker run -d --name frontend --network m2026 -p 5173:5173 frontend:0.1
```

# Deprecated

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

## 1.1 Async 

Starting the Docker container for apache/kafka
```
    docker run -d --name=kafka -p 9092:9092 apache/kafka
```

(-d) - detached
(-p) - port mapping

Stopping running docker containers:
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
