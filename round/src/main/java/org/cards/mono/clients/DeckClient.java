package org.cards.mono.clients;


import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.extern.slf4j.Slf4j;
import org.cards.mono.exceptions.DeckUnavailableException;
import org.cards.mono.model.Card;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class DeckClient {
    private final String url;
    private final RestClient restClient;
    private final CircuitBreaker circuitBreaker;

    public DeckClient(
            RestClient deckRestClient,
            CircuitBreaker deckCircuitBreaker,
            @Value("${deck.service.url}") String url
    ) {
        //this.url = "http://localhost:8001/api/deck/cards";
        this.restClient = deckRestClient;
        this.circuitBreaker = deckCircuitBreaker;
        this.url = url;
    }

    public Map<Long, Card> getCards(int numberOfCards){

        String callUrl = url+"/"+numberOfCards;
        HashMap<Long, Card> cards = new HashMap<>();

        try {
            cards = circuitBreaker.executeSupplier(() ->
                    restClient
                    .get()
                    .uri(callUrl)
                    .retrieve()
                    .body(new ParameterizedTypeReference<HashMap<Long, Card>>() {})
            );
        }
        catch (CallNotPermittedException e){
            log.atError()
                    .addKeyValue("event_type", "Circuit_break")
                    .addKeyValue("Position", "OPEN")
                    .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                    .log("Circuit Breaker is OPEN");
            throw new DeckUnavailableException("Deck temporarily unavailable", e);
        }
        catch (RestClientException e){
            log.atError()
                    .addKeyValue("event_type", "Deck unavailable")
                    .addKeyValue("instance_id", System.getenv("HOSTNAME"))
                    .log("Could not communicate with Deck");
            throw new DeckUnavailableException("Deck temporarily unavailable", e);
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
