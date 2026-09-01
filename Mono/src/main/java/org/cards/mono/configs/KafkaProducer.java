package org.cards.mono.configs;

import org.cards.mono.dtos.CardDTO;
import org.cards.mono.dtos.RoundDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private final KafkaTemplate<String, RoundDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, RoundDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    public void send(RoundDTO roundDTO) {
        kafkaTemplate.send("round-topic", roundDTO);
    }

}
