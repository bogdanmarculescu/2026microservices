package org.cards.mono.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.dtos.PlayerRoundDTO;
import org.cards.mono.model.Card;
import org.cards.mono.model.Round;
import org.cards.mono.services.MonoServicesImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.HasControls;
import java.util.HashMap;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/mono")
public class MonoController {
    private final MonoServicesImpl monoService;

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
        // post round play (card and bit)
        return "";
    }

    @PostMapping("/submitMove")
    public ResponseEntity<String> submitMove(
            @RequestBody PlayerRoundDTO round){
        // post round play (card and bit)

        Card playedCard = round.getPlayedCard();
        Card bidCard = round.getBidCard();
        Long roundId = round.getRoundId();

        System.out.println("playedCard: " + playedCard.getId());
        System.out.println("bidCard: " + bidCard.getId());
        System.out.println("roundId: " + roundId);

        return ResponseEntity.ok("Move received");
    }
}
