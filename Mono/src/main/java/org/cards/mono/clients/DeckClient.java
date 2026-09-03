package org.cards.mono.clients;


import lombok.extern.slf4j.Slf4j;
import org.cards.mono.model.Card;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeckClient {
    private String url;
    private RestTemplate restTemplate;

    public DeckClient(RestTemplateBuilder builder) {
        this.url = "http://localhost:8001/api/deck/cards";
        this.restTemplate = builder.build();
    }

    public Map<Long, Card> getCards(int numberOfCards){

        //TODO: get path parameter to the call
        //TODO: try-catch
        ResponseEntity<HashMap<Long, Card>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<HashMap<Long, Card>>() {}

        );

        return response.getBody();
    }
}



// connect to localhost:8001/api/deck/cards