package org.cards.round.services;

import org.cards.round.model.Round;

public interface ResolverService {
    public String resolveRound(Round round);
    public int resolveForPoints(Round round);
}
