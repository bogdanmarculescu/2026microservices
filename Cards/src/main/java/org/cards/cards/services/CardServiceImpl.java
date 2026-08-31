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
}
