package org.cards.mono.clients;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeckClient {
    private final String url;
    private final RestClient restClient;

    public DeckClient(
            @Value ("${deck.service.url}") String url
    ) {
        //this.url = "http://localhost:8001/api/deck/cards";
        this.restClient = RestClient.create();
        this.url = url;
    }

    public Map<Long, Card> getCards(int numberOfCards){

        String callUrl = url+"/"+numberOfCards;
        HashMap<Long, Card> cards = new HashMap<>();

        try {
            cards = restClient
                    .get()
                    .uri(callUrl)
                    .retrieve()
                    .body(new ParameterizedTypeReference<HashMap<Long, Card>>() {});
        }
        catch (HttpClientErrorException e) {
            e.printStackTrace();
            log.error("Maybe Start the Deck service?");
        }
        return cards;
    }

    public Card getCardById(long id){
        Card card = null;
        String   callUrl = url+"/byId" + "?id=" + id;

        try {
            card = restClient
                    .get()
                    .uri(callUrl)
                    .retrieve()
                    .body(Card.class);
        }
        catch (HttpClientErrorException e){
            e.printStackTrace();
        }
        return card;
    }

}
