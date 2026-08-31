package org.cards.cards;

import org.springframework.boot.SpringApplication;

public class TestCardsApplication {

    public static void main(String[] args) {
        SpringApplication.from(CardsApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
