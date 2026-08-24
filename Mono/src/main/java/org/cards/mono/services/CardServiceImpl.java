package org.cards.mono.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class CardServiceImpl implements CardService {

    private final HashMap<Long, Card> deck = new HashMap<>();

    public CardServiceImpl(){
        ObjectMapper mapper = new ObjectMapper();
        //File jsonFile = new File("Mono/src/main/resources/cards.json");

        try (InputStream inputStream = new ClassPathResource("cards.json").getInputStream()){
            List<Card> readDeck = mapper.readValue(inputStream, new TypeReference<List<Card>>() {});
            Long id = 1L;
            for(Card card: readDeck) {
                System.out.println("Key" + id + ": " + card);
                deck.put(id, card);
                id++;
            }
        }
        catch(IOException e){
           e.printStackTrace();
        }

    }

    @Override
    public HashMap<Long, Card> getCards(int number) {
        List<Card> shuffled = new ArrayList<>(deck.values());
        Collections.shuffle(shuffled);

        HashMap<Long, Card> result = new HashMap<>(number);

        for(int i = 1; i <= number; i++){
            result.put(Long.valueOf(i), shuffled.get(i));
        }
        return result;
    }
}
