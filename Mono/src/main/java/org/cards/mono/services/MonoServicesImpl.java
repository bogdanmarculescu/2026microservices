package org.cards.mono.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.cards.mono.model.Round;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class MonoServicesImpl implements MonoServices {

    private final CardServiceImpl cardService;

    @Override
    public Round getNewRound() {
        Round round = new Round();

        HashMap<Long, Card> cards = cardService.getCards(7);

        //3 cards to player
        round.getPlayerCards().put(Long.valueOf(1), cards.get(Long.valueOf(1)));
        round.getPlayerCards().put(Long.valueOf(2), cards.get(Long.valueOf(2)));
        round.getPlayerCards().put(Long.valueOf(3), cards.get(Long.valueOf(3)));

        // 3 cards to automa
        round.getAutomaCards().put(Long.valueOf(1), cards.get(Long.valueOf(4)));
        round.getAutomaCards().put(Long.valueOf(2), cards.get(Long.valueOf(5)));
        round.getAutomaCards().put(Long.valueOf(3), cards.get(Long.valueOf(6)));

        round.setTopic(cards.get(Long.valueOf(7)));

        //TODO: better id handling, obviously
        round.setId(Long.valueOf(42));

        return round ;

    }

    @Override
    public Round getRound(int id) {
        return null;
    }

    @Override
    public Round playRound(Round round) {
        return null;
    }
}
