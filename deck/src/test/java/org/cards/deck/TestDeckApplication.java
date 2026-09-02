package org.cards.deck;

import org.springframework.boot.SpringApplication;

public class TestDeckApplication {

	public static void main(String[] args) {
		SpringApplication.from(DeckApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
