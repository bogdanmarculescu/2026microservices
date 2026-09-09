package org.cards.resolver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class ResolverApplication {

    public static void main(String[] args) {
        SpringApplication.run(ResolverApplication.class, args);
    }

}
