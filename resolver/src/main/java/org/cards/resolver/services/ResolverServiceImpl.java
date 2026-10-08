package org.cards.resolver.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.dtos.RoundMapper;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.CardRepository;
import org.cards.resolver.model.Round;
import org.cards.resolver.model.RoundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResolverServiceImpl implements ResolverService {

    @Autowired
    private final RoundRepository roundRepository;

    private final RoundMapper roundMapper;
    @Autowired
    private CardRepository cardRepository;

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
                outcome += topic.getCardValue();
                won = true;
            }
            if(player.getCardValue() > automa.getCardValue()){
                // Small win
                outcome += topic.getCardValue();
                won = true;
            }
        }
        else{
            if(automa.getSuite() != topic.getSuite()){
                if(player.getCardValue() > automa.getCardValue()){
                    outcome += topic.getCardValue();
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

        round.setOutcome(outcome);
        round.setSolved(Instant.now());

        Round found = saveRound(round);

        // Simulate a longer delay
        try {
            Thread.sleep(15000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Resolved round: {} @ {}", round.getRoundId(), round.getSolved());
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

    public Card processCard(Card incoming){
        Card result = cardRepository.findById(incoming.getId()).orElse(null);
        if(result == null){
            result = cardRepository.save(incoming);
        }
        return result;
    }

    public Round saveRound(Round round) {
        Round found = roundRepository.findByRoundId(round.getRoundId());

        Card topic = processCard(round.getTopic());
        Card playerCard = processCard(round.getPlayerCard());
        Card automaCard = processCard(round.getAutomaCard());

        Card playerBid = processCard(round.getPlayerBid());
        Card automaBid = processCard(round.getAutomaBid());

        for(Card card : round.getPlayerCards().values()){
            processCard(card);
        }

        for(Card card : round.getAutomaCards().values()){
            processCard(card);
        }

        if(found == null){
            found = roundRepository.save(round);
        }
        else {
            found.setOutcome(round.getOutcome());
        }
        return found;
    }

}
