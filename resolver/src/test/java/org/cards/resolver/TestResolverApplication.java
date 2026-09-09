package org.cards.resolver;

import org.springframework.boot.SpringApplication;

public class TestResolverApplication {

    public static void main(String[] args) {
        SpringApplication.from(ResolverApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
