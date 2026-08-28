package org.cards.mono.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.cards.mono.model.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

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
