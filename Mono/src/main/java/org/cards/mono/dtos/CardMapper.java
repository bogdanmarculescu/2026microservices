package org.cards.mono.dtos;

import org.cards.mono.model.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDTO toDTO(Card card) {
        CardDTO cardDTO = new CardDTO();

        cardDTO.setId(card.getId());
        cardDTO.setCardValue(card.getCardValue());
        cardDTO.setSuite(card.getSuite());
        cardDTO.setFilename(card.getFilename());

        return cardDTO;
    }

    public Card fromDTO(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setCardValue(cardDTO.getCardValue());
        card.setSuite(cardDTO.getSuite());
        card.setFilename(cardDTO.getFilename());
        return card;
    }

}
