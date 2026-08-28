package org.cards.mono.model;

import org.springframework.data.repository.CrudRepository;

public interface RoundRepository extends CrudRepository<Round, Long> {
    Round findByRoundId(Long roundId);
}
