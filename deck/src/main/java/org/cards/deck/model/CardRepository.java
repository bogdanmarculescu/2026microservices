package org.cards.deck.model;

import org.springframework.data.repository.CrudRepository;
import org.cards.deck.model.Card;

public interface CardRepository extends CrudRepository<Card, Long> {
}
