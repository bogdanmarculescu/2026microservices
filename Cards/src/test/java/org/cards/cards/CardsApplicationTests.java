package org.cards.cards;

import org.cards.cards.model.Card;
import org.cards.cards.model.Suite;
import org.cards.cards.services.CardServiceImpl;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class CardsApplicationTests {

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

    @Test
    void theAceOfSpadesTest() throws Exception{
        Card card = new Card();
        card.setId(11L);
        card.setCardValue(11);
        card.setSuite(Suite.SPADES);
        card.setFilename("ace_of_spades.svg");
        // ace_of_spades.svg

        Card retrievedAce = cardService.getCard(card.getId());
        assertEquals(card.getId(), retrievedAce.getId());
    }

    @Test
    void addingACardTest() throws Exception{
        Card card = new Card();
        card.setId(72L);
        card.setCardValue(72);
        card.setSuite(Suite.SPADES);
        card.setFilename("ace_of_spades_motorhead.svg");

        // how many cards before?
        int sizeBefore = cardService.getAllCards().size();

        Card addedAce = cardService.createCard(card);
        // how many cards after?
        int sizeAfter = cardService.getAllCards().size();

        assertEquals(sizeBefore + 1,sizeAfter);
        assertEquals(card.getId(), addedAce.getId());

        // Retrieve and check
        Card retrievedAce = cardService.getCard(card.getId());
        assertEquals(card.getId(), retrievedAce.getId());
    }

    @Test
    void getCardsByIdTest() throws Exception{
        List<Long> ids = List.of(11L, 12L, 23L, 43L);

        Map<Long, Card> cards = cardService.getCardsByIds(ids);

        //test size
        assertEquals(ids.size(),cards.size());
    }
}

