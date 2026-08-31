package org.cards.mono.services;

import org.cards.mono.model.Round;

public interface MonoServices {
    Round getNewRound();
    Round getRound(Long id);
    Round playRound(Round round);
}
