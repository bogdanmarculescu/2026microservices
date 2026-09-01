package org.cards.automa.configs;

import lombok.extern.slf4j.Slf4j;
import org.cards.automa.dtos.CardMapper;
import org.cards.automa.dtos.RoundDTO;
import org.cards.automa.dtos.RoundMapper;
import org.cards.automa.model.Round;
import org.cards.automa.service.AutomaPlayerImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaReceiver {

    private RoundMapper roundMapper;
    private AutomaPlayerImpl automaPlayerImpl;

    public KafkaReceiver() {
        this.roundMapper = new RoundMapper(new CardMapper());
        this.automaPlayerImpl = new AutomaPlayerImpl();
    }

    @KafkaListener(
            topics = "round-topic"
    )
    public void receive(RoundDTO message){
        System.out.println("Round ID ====> " + message.getRoundId());

        Round round = roundMapper.fromDTO(message);
        Round result= automaPlayerImpl.automaPlay(round);

        // Maybe send the resolved round back?

        System.out.println("Outcome =>>>>>>>> " + result.getOutcome());

    }

}
