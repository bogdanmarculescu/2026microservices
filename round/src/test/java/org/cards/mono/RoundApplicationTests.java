package org.cards.mono;

import org.cards.mono.clients.DeckClient;
import org.cards.mono.kafka.KafkaProducer;
import org.cards.mono.model.CardRepository;
import org.cards.mono.model.Round;
import org.cards.mono.model.RoundRepository;
import org.cards.mono.services.RoundServicesImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@Import(TestcontainersConfiguration.class)
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class RoundApplicationTests {

    @Mock
    private KafkaProducer kafkaProducer;

    @Mock
    private DeckClient deckClient;

    @Mock
    private RoundRepository roundRepository;
    @Mock
    private CardRepository cardRepository;

    @InjectMocks
    private RoundServicesImpl rounds;

    @Test
    void contextLoads() {
    }

    @Test
    void newRoundTest(){
        // Arrange
        when(deckClient.getCards(anyInt()))
                .thenReturn(new HashMap<>());

        // Act
        rounds.getNewRound();

        // Assert
        verify(deckClient).getCards(anyInt());
    }


}
