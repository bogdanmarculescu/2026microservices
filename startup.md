# Startup Procedure

## 1. Start backend


### 1.1 Start Cards Service

```
    cd Cards/
    mvn clean verify -fae
    mvn spring-boot:run
```

### 1.2 Start Mono/Main

```
    cd Mono/
    mvn clean verify -fae
    mvn spring-boot:run
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

