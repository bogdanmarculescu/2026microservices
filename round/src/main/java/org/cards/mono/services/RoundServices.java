package org.cards.mono.services;

import org.cards.mono.model.Round;

public interface RoundServices {
    Round getNewRound();
    Round getRound(int id);
    Round playRound(Round round);
}
