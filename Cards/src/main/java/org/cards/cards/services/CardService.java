package org.cards.cards.services;


import org.cards.cards.model.Card;

import java.util.Iterator;
import java.util.Map;

public interface CardService {
    Map<Long, Card> getCards(int number);
    Card getCard(Long id);
    Card createCard(Card card);
    Map<Long, Card> getAllCards();
    Map<Long, Card> getCardsByIds(Iterable<Long> ids);
}
