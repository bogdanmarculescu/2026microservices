package org.cards.mono.kafka;


import lombok.extern.slf4j.Slf4j;
import org.cards.mono.dtos.RoundDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, RoundDTO> kafkaTemplate;

    public void send(RoundDTO message) {
        //log.info("Sending Kafka Message: {}", message);

        this.sendWithTopic("round-test", message);
        //log.info("SENT TO KAFKA ========= {}", message);

        //log.warn("THIS IS A WARNING!");
    }

    public void sendWithTopic(String topic, RoundDTO message) {
        log.info("Sending Kafka Message: {}", message);

        kafkaTemplate.send(topic, message);
        log.info("SENT TO KAFKA ========= {}", message);

        //log.warn("THIS IS A WARNING!");
    }
}
