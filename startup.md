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