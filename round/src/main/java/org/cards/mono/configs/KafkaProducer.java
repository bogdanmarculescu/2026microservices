package org.cards.mono.configs;

import lombok.extern.slf4j.Slf4j;
import org.cards.mono.dtos.RoundDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducer {

    private final KafkaTemplate<String, RoundDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, RoundDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendRound(RoundDTO message) {
        kafkaTemplate.send("round-complete", message)
                .whenComplete((result, exception) -> {
                    if (exception != null) {
                        log.error("Kafka send FAILED", exception);
                    } else {
                        var metadata = result.getRecordMetadata();

                        log.info(
                                "Kafka send OK: topic={}, partition={}, offset={}",
                                metadata.topic(),
                                metadata.partition(),
                                metadata.offset()
                        );
                    }
                });
    }

}
