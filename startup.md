# Startup Procedure

## 1. Start backend

```
    cd Mono/
    mvn clean verify -fae
    mvn spring-boot:run
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