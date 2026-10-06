package org.cards.round;

import org.springframework.boot.SpringApplication;

public class TestMonoApplication {

    public static void main(String[] args) {
        SpringApplication.from(RoundApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
