package org.cards.deck.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import org.cards.deck.services.CardServiceImpl;
import org.cards.deck.model.Card;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deck")
public class DeckController{

    public final CardServiceImpl cardServiceImpl;

    @GetMapping("/cards")
    public ResponseEntity<Map<Long, Card>> getCards(){

        Map<Long, Card> result = cardServiceImpl.getCards(7);
        System.out.println("I got this many cards: ====> " + result.size());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/cards/{number}")
    public ResponseEntity<Map<Long, Card>> getNoCards(
            @PathVariable int number
    ){
        System.out.println("I got this as a parameter: ====> " + number);
        Map<Long, Card> result = cardServiceImpl.getCards(number);
        System.out.println("I got this many cards: ====> " + result.size());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/cards/byId")
    public ResponseEntity<Card> getCard(
            @RequestParam Long id
    ){
        log.debug("Retrieving: ====> " + id);
        Card result =  cardServiceImpl.getCardById(id);
        return ResponseEntity.ok(result);

    }

    @PostMapping("/cards")
    public ResponseEntity<Card> createCard(
            @RequestBody Card card
    ){
        log.debug("Creating: ====> " + card);
        Card result =  cardServiceImpl.createCard(card);
        return ResponseEntity.ok(result);
    }

}