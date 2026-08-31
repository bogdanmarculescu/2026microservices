package org.cards.mono.clients;


import org.cards.mono.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDate;
import java.util.HashMap;

@Service
public class DeckClient {
    private final String url;
    private final RestTemplate restTemplate;

    public DeckClient(
            RestTemplateBuilder builder
    ) {
        this.url = "http://localhost:8001/api/deck";
        this.restTemplate = builder.build();
    }

    public HashMap<Long, Card> getCards(int noCards) {

        String callUrl = this.url + "/" + noCards;
        ResponseEntity<HashMap<Long, Card>> responseEntity = restTemplate.exchange(
                callUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HashMap<Long, Card>>() {}

        );

        System.out.println("Succesfully connected to Deck - " + LocalDate.now().toString());
        return responseEntity.getBody();
    }

}
