package org.cards.mono.services;

import org.cards.mono.model.Round;

public interface ResolverService {
    public String resolveRound(Round round);
    public int resolveForPoints(Round round);
}
