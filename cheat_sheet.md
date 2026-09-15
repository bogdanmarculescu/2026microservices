## 1. Frontend

Install dependencies:
```
    npm install
```
Run in Dev mode:
```
    npm run dev
```
Find the frontend at (default):
```
    localhost:5173
```

## 2. Maven

Clean, compile, install:
```
    mvn clean install
    mvn clean package
```
Verify (run your tests):
```
    mvn clean verify -fae
```
NOTE: "-fae" - fail at end - run all my tests and receive a report regarding all the failures. 
It is optional. If skipped, the first test to fail causes subsequent test runs to stop. 

Package creates a .jar file, which can be run in Terminal:
```
    java -jar [filename.jar]
    java -jar deck-0.0.1-SNAPSHOT.jar
```

Run:
```
    mvn spring-boot:run
```
Adding parameters (for example - setting the port):
```
    mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=8005
```


## 3. Infrastructure:
Network setup
```
    docker network create [network_name]
    docker network create micro2026
```

Kafka:
```
    docker run -d --name=kafka -p 9092:9092 apache/kafka
```
If using RabbitMQ:
```
    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management
```

Postgres Example:
```
docker run -it --rm --name postgres \
  -e POSTGRES_USER=serviceuser \
  -e POSTGRES_PASSWORD=servicepwd \
  -e POSTGRES_DB=deck \
  -p 5432:5432 \
  -v postgres_data:/var/lib/postgresql/data \
  --network micro2026 \
  -d postgres:15
```

## 4. Building Docker images

#### Option 1:
Compile and package your project:
```
    mvn clean package
```
Build image (from Dockerfile):
```
    docker build -t [container_name] .
    docker build -t deck:0.0.1 .
```

#### Option 2:
Running Docker images:
```
    docker run -d --name deck --network micro2026 -p 8001:8001 deck
    docker run -d --name frontend
```

#### Statup procedure:
```
    docker network create micro2026
    docker run -d --name=kafka --network=micro2026 -p 9092:9092 apache/kafka 
    
    docker run -d --name frontend --network micro2026 -p 5173:5173 frontend
    
    docker run -d --name [service_name] --network micro2026 [-p 8000:8000] [service]:0.0.1-SNAPSHOT
    docker run -d --name deck --network micro2026 -p 8001:8001 deck:0.0.1-SNAPSHOT
```