package org.cards.resolver.kafka;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaReceiver {

    @PostConstruct
    public void init() {
        log.info("******** KAFKA RECEIVER WAS CREATED ********");
    }

    @KafkaListener(
            id = "roundTestListener",
            topics = "round-test",
            groupId = "round-test-debug"
    )
    public void receiveText(String message) {
        log.info("******** RECEIVED: {} ********", message);
    }

}
