package org.cards.mono.clients;


import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeckClient {
    private String url;
    private RestTemplate restTemplate;

    public DeckClient(
            RestTemplateBuilder builder,
            @Value ("${deck.service.url}") String url
    ) {
        //this.url = "http://localhost:8001/api/deck/cards";
        this.restTemplate = builder.build();
        this.url = url;
    }

    public Map<Long, Card> getCards(int numberOfCards){

        String callUrl = url+"/"+numberOfCards;
        HashMap<Long, Card> cards = new HashMap<>();

        try {
            ResponseEntity<HashMap<Long, Card>> response = restTemplate.exchange(
                    callUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<HashMap<Long, Card>>() {}
            );
            cards = response.getBody();
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
            ResponseEntity<Card> response = restTemplate.exchange(
                        callUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<Card>() {}
            );

            card = response.getBody();

        }
        catch (HttpClientErrorException e){
            e.printStackTrace();
        }

        return card;
    }

}



// connect to localhost:8001/api/deck/cards