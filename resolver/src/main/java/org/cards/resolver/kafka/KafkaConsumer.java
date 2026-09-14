package org.cards.resolver.kafka;

import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.dtos.RoundDTO;
import org.cards.resolver.dtos.RoundMapper;
import org.cards.resolver.model.Round;
import org.cards.resolver.services.ResolverServiceImpl;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaConsumer {

    private final RoundMapper roundMapper;
    private final ResolverServiceImpl resolverServiceImpl;

    public KafkaConsumer(RoundMapper roundMapper, ResolverServiceImpl resolverServiceImpl) {
        this.roundMapper = roundMapper;
        this.resolverServiceImpl = resolverServiceImpl;
    }

    @KafkaListener(
            topics = "round-test",
            groupId = "test-consumer"
    )
    public void receiveMessage(RoundDTO message) {

        log.info("KAFKA RECEIVED ========= {}", message);

        Round received = roundMapper.fromDTO(message);
        int outcome = resolverServiceImpl.resolveForPoints(received);

        log.info("OUTCOME ========= {}", outcome);
    }
}
