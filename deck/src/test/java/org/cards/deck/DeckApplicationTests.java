package org.cards.deck;

import org.cards.deck.model.Card;
import org.cards.deck.services.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
class DeckApplicationTests {

	@Autowired
	private CardServiceImpl cardService;

	@Test
	void contextLoads() {
	}

	@Test
	void cardServiceSizeTest() throws  Exception {
		Map<Long, Card> cards = cardService.getCards(7);
		assertEquals(7 ,cards.size());
	}

	void cardServiceSizeTestParam(int i) throws  Exception {
		Map<Long, Card> cards = cardService.getCards(i);
		assertEquals(i ,cards.size());
	}

	@Test
	void multipleSizeTests() throws Exception{
		cardServiceSizeTestParam(1);
		cardServiceSizeTestParam(11);
	}

}
