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

import java.awt.desktop.SystemSleepEvent;
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
        // Log at the START of processing

        log.atInfo()
                .addKeyValue("round_id", round.getRoundId())
                .addKeyValue("event_type", "round_resolution_started")
                .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                .log("Round Resolution Started");

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

        log.info("Resolved round: {} @ {}", round.getRoundId(), round.getSolved());

        log.atInfo()
                .addKeyValue("round_id", round.getRoundId())
                .addKeyValue("event_type", "round_resolution_completed")
                .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                .log("Round Resolution Complete");

        log.atError()
                .addKeyValue("event_type", "Panic")
                .addKeyValue("stress_level", "much")
                .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                .log("Error");

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

        if(found == null){
            found = new Round();
        }

        found.setOutcome(round.getOutcome());

        found.setTopic(processCard(round.getTopic()));
        found.setPlayerCard(processCard(round.getPlayerCard()));
        found.setAutomaCard(processCard(round.getAutomaCard()));

        found.setPlayerBid(processCard(round.getPlayerBid()));
        found.setAutomaBid(processCard(round.getAutomaBid()));

        long l=1;
        for(Card card : round.getPlayerCards().values()){
            found.getPlayerCards().put(l, processCard(card));
            l++;
        }
        l=1;
        for(Card card : round.getAutomaCards().values()){
            found.getAutomaCards().put(l, processCard(card));
            l++;
        }

        roundRepository.save(found);

        return found;
    }

}
