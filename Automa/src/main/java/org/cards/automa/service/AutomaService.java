package org.cards.automa.service;

import lombok.extern.slf4j.Slf4j;
import org.cards.automa.dtos.RoundDTO;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AutomaService {

    public AutomaService() {
    }

    @KafkaListener(
            topics = "round-topic"
    )
    public void receive(RoundDTO message){
        System.out.println("Round ID ====> " + message.getRoundId());
    }

}
