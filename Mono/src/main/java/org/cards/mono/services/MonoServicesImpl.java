package org.cards.mono.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.clients.DeckClient;
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

    //private final CardServiceImpl cardService;
    private final AutomaPlayerImpl automaPlayerImpl;
    private final ResolverServiceImpl resolverServiceImpl;

    private final DeckClient deckClient;

    private final RoundRepository roundRepository;
    private final CardRepository cardRepository;

    @Override
    public Round getNewRound() {
        Round round = new Round();

        //Map<Long, Card> cards = cardService.getCards(7);

        Map<Long, Card> retrievedCards = deckClient.getCards(7);
        HashMap<Long, Card> cards = new HashMap<Long, Card>();

        for (Map.Entry<Long, Card> entry : retrievedCards.entrySet()) {
            Card savedCard = cardRepository.save(entry.getValue());
            cards.put(entry.getKey(), savedCard);
        }


        for(Card card : cards.values()){
            cardRepository.save(card);
        }
        System.out.println("Remote call =>> " + cards.size());

        //Replace this with an http call to Deck External service

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

        //Example of retrieving individual cards from an external Deck service
        Card retrievedCard = retrievedCards.get(1L);
        log.info("Card retrieved => " + retrievedCard.getFilename());

        return savedRound ;
    }

    @Override
    public Round getRound(int id) {
        return null;
    }

    @Override
    public Round playRound(Round round) {
        Round existingRound = roundRepository.findByRoundId(round.getRoundId());
        existingRound.setPlayerCard(round.getPlayerCard());
        existingRound.setPlayerBid(round.getPlayerBid());

        Round fullRound = automaPlayerImpl.automaPlay(existingRound);

        //fullRound.setOutcome(resolverServiceImpl.resolveRound(fullRound));
        fullRound.setOutcome(resolverServiceImpl.resolveForPoints(fullRound));
        return fullRound;
    }
}
