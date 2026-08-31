package org.cards.automa;

import org.springframework.boot.SpringApplication;

public class TestAutomaApplication {

    public static void main(String[] args) {
        SpringApplication.from(AutomaApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
