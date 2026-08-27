package org.cards.mono;

import org.cards.mono.model.Card;
import org.cards.mono.services.CardService;
import org.cards.mono.services.CardServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class MonoApplicationTests {

    @Autowired
    private final CardServiceImpl cardService =  new CardServiceImpl();

    @Test
    void contextLoads() {
    }

    @Test
    void cardServiceSizeTest() throws  Exception {
        HashMap<Long, Card> cards = cardService.getCards(7);
        assertEquals(7 ,cards.size());
    }

    void cardServiceSizeTestParam(int i) throws  Exception {
        HashMap<Long, Card> cards = cardService.getCards(i);
        assertEquals(i ,cards.size());
    }

    @Test
    void multipleSizeTests() throws Exception{
        cardServiceSizeTestParam(1);
        cardServiceSizeTestParam(11);
    }

}
