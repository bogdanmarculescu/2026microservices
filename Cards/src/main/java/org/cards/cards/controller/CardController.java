package org.cards.cards.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.cards.model.Card;
import org.cards.cards.model.CardRepository;
import org.cards.cards.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deck")
public class CardController {
    private final CardService cardService;
    private final CardRepository cardRepository;

    @GetMapping("/{noCards}")
    public ResponseEntity<Map<Long, Card>> getCards(
            @PathVariable int noCards){
        Map<Long, Card> cards = cardService.getCards(noCards);

        return ResponseEntity.ok(cards);
    }
}
