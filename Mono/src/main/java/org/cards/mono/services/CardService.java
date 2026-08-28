package org.cards.mono.services;


import org.cards.mono.model.Card;

import java.util.HashMap;
import java.util.Map;

public interface CardService {
    Map<Long, Card> getCards(int number);
}
