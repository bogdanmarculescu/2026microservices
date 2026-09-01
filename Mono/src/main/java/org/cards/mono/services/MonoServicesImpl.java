package org.cards.mono.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.clients.DeckClient;
import org.cards.mono.configs.KafkaProducer;
import org.cards.mono.dtos.RoundDTO;
import org.cards.mono.dtos.RoundMapper;
import org.cards.mono.model.Card;
import org.cards.mono.model.CardRepository;
import org.cards.mono.model.Round;
import org.cards.mono.model.RoundRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonoServicesImpl implements MonoServices {

    private final DeckClient cardService;
    private final AutomaPlayerImpl automaPlayerImpl;
    private final ResolverServiceImpl resolverServiceImpl;

    private final RoundRepository roundRepository;
    private final CardRepository cardRepository;

    private final KafkaProducer kafkaProducer;
    private final RoundMapper roundMapper;

    @Override
    public Round getNewRound() {
        Round round = new Round();

        HashMap<Long, Card> retrievedCards = cardService.getCards(7);
        HashMap<Long, Card> cards = new HashMap<Long, Card>();

        for (Map.Entry<Long, Card> entry : retrievedCards.entrySet()) {
            Card savedCard = cardRepository.save(entry.getValue());
            cards.put(entry.getKey(), savedCard);
        }


        for(Card card : cards.values()){
            cardRepository.save(card);
        }
        //3 cards to player
        round.getPlayerCards().put(1L, cards.get(1L));
        round.getPlayerCards().put(2L, cards.get(2L));
        round.getPlayerCards().put(3L, cards.get(3L));

        // 3 cards to automa
        round.getAutomaCards().put(1L, cards.get(4L));
        round.getAutomaCards().put(2L, cards.get(5L));
        round.getAutomaCards().put(3L, cards.get(6L));

        round.setTopic(cards.get(7L));

        Round savedRound = roundRepository.save(round);

        return savedRound ;

    }

    @Override
    public Round getRound(Long id) {
        Round round = roundRepository.findByRoundId(id);
        return round;
    }

    @Override
    public Round playRound(Round round) {
        Round existingRound = roundRepository.findByRoundId(round.getRoundId());
        existingRound.setPlayerCard(round.getPlayerCard());
        existingRound.setPlayerBid(round.getPlayerBid());

        Round fullRound = automaPlayerImpl.automaPlay(existingRound);

        // Kafka send here?
        RoundDTO automaRoundDTO = roundMapper.toRoundDTO(fullRound);
        System.out.println("Sending to Kafka");
        kafkaProducer.send(automaRoundDTO);
        System.out.println("Sent: " + automaRoundDTO.getRoundId());

        fullRound.setOutcome(resolverServiceImpl.resolveRound(fullRound));
        return fullRound;
    }
}
