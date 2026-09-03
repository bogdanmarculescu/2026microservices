package org.cards.deck.services;


import org.cards.deck.model.Card;

import java.util.HashMap;
import java.util.Map;

public interface CardService {
    Map<Long, Card> getCards(int number);
    Card getCardById(long id);
}
