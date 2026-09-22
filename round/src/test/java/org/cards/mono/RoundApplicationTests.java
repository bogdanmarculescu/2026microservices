package org.cards.mono;

import org.cards.mono.kafka.KafkaProducer;
import org.cards.mono.model.Card;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
@ActiveProfiles("test")
class RoundApplicationTests {

    @MockitoBean
    private KafkaProducer kafkaProducer;

    @Test
    void contextLoads() {
    }

}
