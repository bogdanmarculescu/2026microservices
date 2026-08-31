package org.cards.cards.services;


import org.cards.cards.model.Card;

import java.util.Map;

public interface CardService {
    Map<Long, Card> getCards(int number);
}
