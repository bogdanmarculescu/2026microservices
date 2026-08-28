package org.cards.mono.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.cards.mono.model.Card;
import org.cards.mono.model.CardRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Component
public class DeckInitializer implements ApplicationRunner {

    private final CardRepository cardRepository;

    public DeckInitializer(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    @PostConstruct
    public void initializeDeck() {
        if (cardRepository.count() > 0) {
            return;
        }

        ObjectMapper mapper = new ObjectMapper();

        try (InputStream inputStream =
                     new ClassPathResource("cards.json").getInputStream()) {

            List<Card> cards = mapper.readValue(
                    inputStream,
                    new TypeReference<List<Card>>() {}
            );

            long id = 1;

            for (Card card : cards) {
                card.setId(id++);
            }

            cardRepository.saveAll(cards);

        } catch (IOException e) {
            throw new RuntimeException("Could not load cards.json", e);
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initializeDeck();
    }
}