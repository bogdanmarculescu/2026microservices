package org.cards.mono.services;

import org.cards.mono.model.Card;
import org.cards.mono.model.Round;
import org.springframework.stereotype.Service;

@Service
public class ResolverServiceImpl implements ResolverService {
    @Override
    public String resolveRound(Round round) {
        // SIMPLE RESOLVER
        // TODO: some cleverer way to resolve a round

        Card player = round.getPlayerCard();
        Card automa = round.getAutomaCard();
        Card topic = round.getTopic();

        String outcome = "loss";
        if(player.getSuite() == topic.getSuite()){
            if(automa.getSuite() != topic.getSuite()){
                outcome = "win";
            }
            if(player.getCardValue() > automa.getCardValue()){
                outcome = "win";
            }
        }
        else{
            if(automa.getSuite() != topic.getSuite()){
                if(player.getCardValue() > automa.getCardValue()){
                    outcome = "win";
                }
            }
        }

        return outcome;
    }

    @Override
    public int resolveForPoints(Round round) {
        // SIMPLE RESOLVER
        // TODO: some cleverer way to resolve a round

        Card player = round.getPlayerCard();
        Card automa = round.getAutomaCard();
        Card topic = round.getTopic();

        int outcome = 0;
        boolean won = false;
        if(player.getSuite() == topic.getSuite()){
            if(automa.getSuite() != topic.getSuite()){
                //player win
                //outcome += topic.getCardValue();
                won = true;
            }
            if(player.getCardValue() > automa.getCardValue()){
                // Small win
                //outcome += topic.getCardValue();
                won = true;
            }
        }
        else{
            if(automa.getSuite() != topic.getSuite()){
                if(player.getCardValue() > automa.getCardValue()){
                    //outcome += topic.getCardValue();
                    won = true;
                }
            }
        }
        if(won) {
            outcome += topic.getCardValue();
        }
        else{
            outcome -= topic.getCardValue();
        }

        return outcome;
    }
}
