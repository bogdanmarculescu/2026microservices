package org.cards.resolver.dtos;

import lombok.RequiredArgsConstructor;
import org.cards.resolver.dtos.CardDTO;
import org.cards.resolver.model.Card;
import org.cards.resolver.model.CardRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardMapper {

    private final CardRepository cardRepository;

    public CardDTO toDTO(Card card) {
        CardDTO cardDTO = new CardDTO();

        cardDTO.setId(card.getId());
        cardDTO.setCardValue(card.getCardValue());
        cardDTO.setSuite(card.getSuite());
        cardDTO.setFilename(card.getFilename());

        return cardDTO;
    }

    public Card fromDTOfull(CardDTO cardDTO) {
        Card card = new Card();
        card.setId(cardDTO.getId());
        card.setCardValue(cardDTO.getCardValue());
        card.setSuite(cardDTO.getSuite());
        card.setFilename(cardDTO.getFilename());
        return card;
    }

    public Card fromDTO(CardDTO cardDTO) {
        Card card = cardRepository.findById(cardDTO.getId()).orElse(null);
        if (card == null) {
            card = this.fromDTOfull(cardDTO);
            cardRepository.save(card);
        }
        return card;
    }

    public Card processCard(Card input){
        Card card = cardRepository.findById(input.getId()).orElse(null);
        if (card == null) {
            card = input;
            cardRepository.save(card);
        }
        return card;
    }

}