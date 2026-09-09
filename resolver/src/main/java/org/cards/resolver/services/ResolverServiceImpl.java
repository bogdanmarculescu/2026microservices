package org.cards.resolver.services;

import lombok.RequiredArgsConstructor;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.Round;
import org.cards.resolver.model.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolverServiceImpl implements ResolverService {

    @Autowired
    private final RoundRepository roundRepository;

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

        roundRepository.save(round);

        return outcome;
    }

    @Override
    public Round getRound(Long roundId) {
        Round result = roundRepository.findByRoundId(roundId);
        return result;
    }

    @Override
    public List<Round> getRounds() {
        List<Round> result = new ArrayList<Round>();
        roundRepository.findAll().forEach(result::add);
        return result;
    }


}
