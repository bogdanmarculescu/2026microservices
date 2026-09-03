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
        //TODO: This needs to be a parameter
        Map<Long, Card> result = cardServiceImpl.getCards(7);
        System.out.println("I got this many cards: ====> " + result.size());

        return ResponseEntity.ok(result);
    }

}