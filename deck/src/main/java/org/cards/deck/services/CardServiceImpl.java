package org.cards.deck.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.deck.model.Card;
import org.cards.deck.model.CardRepository;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {

    private final CardRepository cardRepository;

    @Override
    public Map<Long, Card> getCards(int number) {

        //Log that I am starting to prepare cards
        log.atInfo()
                .addKeyValue("event_type", "cards_request")
                .addKeyValue("number_requested", number)
                .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                .log("Requested Cards");

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

        // Log that I am ready with the cards
        log.atInfo()
                .addKeyValue("event_type", "cards_delivered")
                .addKeyValue("number_provided", result.size())
                .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                .log("Provided Cards");

        return result;
    }

    @Override
    public Card getCardById(long id) {
        Card card = cardRepository.findById(id).orElse(null);
        return card;
    }

    @Override
    public Card createCard(Card card) {

        if(card == null){
            return null;
        }

        boolean complete = Stream.of(
                card.getId(),
                card.getFilename(),
                card.getSuite()
        ).allMatch(Objects::nonNull);

        if(complete){
            return cardRepository.save(card);
        }
        return null;
    }


}
