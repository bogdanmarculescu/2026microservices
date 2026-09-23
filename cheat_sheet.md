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
    
    
    docker run -d --name kafka --network m2026 -p 9092:9092 -e KAFKA_NODE_ID=1 -e KAFKA_PROCESS_ROLES=broker,controller -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,HOST:PLAINTEXT,DOCKER:PLAINTEXT -e KAFKA_LISTENERS=CONTROLLER://:9091,HOST://:9092,DOCKER://:9093 -e KAFKA_ADVERTISED_LISTENERS=HOST://localhost:9092,DOCKER://kafka:9093 -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9091 -e KAFKA_INTER_BROKER_LISTENER_NAME=DOCKER -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 apache/kafka:latest

```
If using RabbitMQ:
```
    docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 --network micro2025 rabbitmq:4-management
```

Postgres Example:
```
docker run -it --rm --name postgres -e POSTGRES_USER=serviceuser -e POSTGRES_PASSWORD=servicepwd -e POSTGRES_DB=deck -p 5432:5432 -v postgres_data:/var/lib/postgresql/data --network micro2026 -d postgres:15
  
  docker run -it --rm --name postgres -e POSTGRES_USER=serviceuser -e POSTGRES_PASSWORD=servicepwd -e POSTGRES_DB=deck -p 5432:5432 -v postgres_data:/var/lib/postgresql/data -d postgres:15

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
    docker run -d --name deck --network m2026 -p 8001:8001 -e SPRING_PROFILES_ACTIVE=docker deck:0.0.1
    docker run -d --name frontend --network m2026 -p 5173:5173 frontend
```

#### Statup procedure:

- [ ] Create network
```
    docker network create micro2026
```

- Infrastructure: Kafka
```
    docker run -d --name kafka --network micro2026 -p 9092:9092 -e KAFKA_NODE_ID=1 -e KAFKA_PROCESS_ROLES=broker,controller -e KAFKA_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,HOST:PLAINTEXT,DOCKER:PLAINTEXT -e KAFKA_LISTENERS=CONTROLLER://:9091,HOST://:9092,DOCKER://:9093 -e KAFKA_ADVERTISED_LISTENERS=HOST://localhost:9092,DOCKER://kafka:9093 -e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER -e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9091 -e KAFKA_INTER_BROKER_LISTENER_NAME=DOCKER -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 apache/kafka:latest
```

- [ ] Infrastructure: Deck service postgres database

```
docker run -it --rm --name postgres -e POSTGRES_USER=serviceuser -e POSTGRES_PASSWORD=servicepwd -e POSTGRES_DB=deck -p 5432:5432 -v postgres_data:/var/lib/postgresql/data --network micro2026 -d postgres:15
```

- [ ] Deck service
``` 
    docker run -d --name deck --network micro2026 -p 8001:8001 deck:0.0.1-SNAPSHOT
    
    OR
    
    mvn spring-boot:run
```

- [ ] Round Service

```
    mvn spring-boot:run
```

- [ ] Resolver Service

```
    docker run -d --name resolver --network micro2026 -p 8006:8006 resolver:0.0.1-SNAPSHOT
```

- [ ] Running a generic service

```
    docker run -d --name [service_name] --network micro2026 [-p 8000:8000] [service]:0.0.1-SNAPSHOT
```

## Consul
```
    docker run --name=consul-server -d -p 8500:8500 -p 8600:8600/udp hashicorp/consul consul agent -server -ui -node=server-1 -bootstrap-expect=1 -client=0.0.0.0 -data-dir=/consul/data
    
    docker run -d --name consul -p 8500:8500 hashicorp/consul:latest agent -dev -client=0.0.0.0
    
```
## Gateway
```
docker run -d --name gateway --network m2026 -p 8100:8100 gateway:0.0.1-SNAPSHOT
```