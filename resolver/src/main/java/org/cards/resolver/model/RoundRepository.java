package org.cards.resolver.model;

import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Long> {
    Round findByRoundId(Long roundId);
}
