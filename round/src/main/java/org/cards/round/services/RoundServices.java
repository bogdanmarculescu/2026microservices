package org.cards.round.services;

import org.cards.round.model.Round;

public interface RoundServices {
    Round getNewRound();
    Round getRound(int id);
    Round playRound(Round round);
}
