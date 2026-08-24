package org.cards.mono.services;


import org.cards.mono.model.Card;

import java.util.HashMap;

public interface CardService {
    HashMap<Long, Card> getCards(int number);
}
