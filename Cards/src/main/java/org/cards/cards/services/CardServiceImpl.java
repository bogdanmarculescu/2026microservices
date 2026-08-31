package org.cards.cards.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.cards.model.Card;
import org.cards.cards.model.CardRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Map<Long, Card> getCards(int number) {
        List<Card> shuffled = new ArrayList<>();
        cardRepository.findAll().forEach(shuffled::add);

        if(number > shuffled.size()){
            throw new IllegalArgumentException("Not enough cards");
        }

        Collections.shuffle(shuffled);

        Map<Long, Card> result = IntStream.range(1, number+1)
                .boxed()
                .collect(Collectors.toMap(
                        Long::valueOf,
                        shuffled::get
                ));
        return result;
    }

    @Override
    public Card getCard(Long id) {
        Card result = cardRepository.findById(id).orElse(null);
        return result;
    }

    @Override
    public Card createCard(Card card) {
        Card result = cardRepository.save(card);
        return result;
    }

    @Override
    public Map<Long, Card> getAllCards() {
        Iterable<Card> allCards = cardRepository.findAll();
        HashMap<Long, Card> result = new HashMap<>();

        Long index = 0L;
        for (Card card : allCards) {
            result.put(index, card);
            index++;
        }

        return result;
    }

    @Override
    public Map<Long, Card> getCardsByIds(Iterable<Long> ids) {
        HashMap<Long, Card> result = new HashMap<>();

        Long index = 1L;
        for (Long id : ids){
            Card card = cardRepository.findById(id).orElse(null);
            if(card != null){
                result.put(index, card);
                index++;
            }
        }
        return result;
    }

}
