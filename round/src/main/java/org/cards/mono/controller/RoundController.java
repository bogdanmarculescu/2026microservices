package org.cards.mono.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.cards.mono.model.Round;
import org.cards.mono.services.RoundServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/round")
public class RoundController {
    private final RoundServicesImpl monoService;

    @GetMapping
    public Round getNewRound() {
        // get a completely new round
        return monoService.getNewRound();
    }

    @GetMapping("/{id}")
    public String getRound(@PathVariable String id) {
        // get round by id
        return "";
    }

    @PostMapping
    public String playRound(
            @RequestBody String round){
        // post round play (card and bid)
        return "";
    }

    @PostMapping("/submitMove")
    public ResponseEntity<String> submitMove(
            @RequestBody Round round){
        // post round play (card and bit)

        Card playedCard = round.getPlayerCard();
        Card bidCard = round.getPlayerBid();
        Long roundId = round.getRoundId();

        System.out.println("playedCard: " + playedCard.getId());
        System.out.println("bidCard: " + bidCard.getId());
        System.out.println("roundId: " + roundId);

        //Add the automa card play

        Round solvedRound = monoService.playRound(round);
        log.info("Outcome: " + solvedRound.getOutcome());

        return ResponseEntity.ok("Points: " + solvedRound.getOutcome());
    }
}
