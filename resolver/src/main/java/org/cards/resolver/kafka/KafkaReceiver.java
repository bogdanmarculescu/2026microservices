package org.cards.resolver.kafka;


import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.dtos.RoundDTO;
import org.cards.resolver.model.Round;
import org.cards.resolver.services.ResolverServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaReceiver {

    private final ResolverServiceImpl resolverService;

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

    @KafkaListener(
            id = "roundListener",
            topics = "round-complete",
            groupId = "round"
    )
    public void receiveRound(RoundDTO message) {
        int result = resolverService.resolveForPoints(message);
        log.info("******** RECEIVED: {} ********", message.toString());
        log.info("***** Result ->>> {}",  result);


    }

}
