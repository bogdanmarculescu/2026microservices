package org.cards.mono.services;

import org.cards.mono.model.Round;

public interface MonoServices {
    Round getNewRound();
    Round getRound(int id);
    Round playRound(Round round);
}
