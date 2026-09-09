package org.cards.resolver.services;

import org.cards.resolver.dtos.RoundDTO;
import org.cards.resolver.model.Round;

import java.util.List;

public interface ResolverService {
    public int resolveForPoints(Round round);
    public int resolveForPoints(RoundDTO round);
    public Round getRound(Long roundId);
    public List<Round> getRounds();
}
