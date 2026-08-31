package org.cards.cards.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.cards.model.Card;
import org.cards.cards.model.CardRepository;
import org.cards.cards.services.CardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deck")
public class CardController {
    private final CardService cardService;

    @GetMapping("/{noCards}")
    public ResponseEntity<Map<Long, Card>> getCards(
            @PathVariable int noCards){
        Map<Long, Card> cards = cardService.getCards(noCards);

        return ResponseEntity.ok(cards);
    }

    @GetMapping
    public Card getCard(@RequestParam Long cardId){
        Card result = cardService.getCard(cardId);
        return result;
    }

    @PostMapping
    public ResponseEntity<Card> createCard(@RequestBody Card card){
        Card result = cardService.createCard(card);
        return ResponseEntity.ok(result);
    }
}
