package org.cards.resolver.services;

import lombok.RequiredArgsConstructor;
import org.cards.resolver.dtos.CardMapper;
import org.cards.resolver.dtos.RoundDTO;
import org.cards.resolver.dtos.RoundMapper;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.CardRepository;
import org.cards.resolver.model.Round;
import org.cards.resolver.model.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ResolverServiceImpl implements ResolverService {

    private final RoundRepository roundRepository;
    private final CardRepository cardRepository;

    private final CardMapper cardMapper;
    private final RoundMapper roundMapper;

    @Override
    public int resolveForPoints(Round round) {
        // SIMPLE RESOLVER
        // TODO: some cleverer way to resolve a round

        Card player = cardMapper.processCard(round.getPlayerCard());
        Card automa = cardMapper.processCard(round.getAutomaCard());
        Card topic = cardMapper.processCard(round.getTopic());

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
    public int resolveForPoints(RoundDTO round) {
        Round result = roundMapper.fromDTO(round);
        return resolveForPoints(result);
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

    public void processCards(Round round){
        Card topic = cardRepository.findById(round.getTopic().getId())
                .orElseThrow();
    }


}
