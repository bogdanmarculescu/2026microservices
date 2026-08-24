package org.cards.mono;

import org.springframework.boot.SpringApplication;

public class TestMonoApplication {

    public static void main(String[] args) {
        SpringApplication.from(MonoApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
