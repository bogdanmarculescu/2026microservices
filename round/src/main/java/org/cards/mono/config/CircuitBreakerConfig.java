package org.cards.mono.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class CircuitBreakerConfig {

    @Bean
    public CircuitBreaker deckCircuitBreaker() {

        var config = io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
                .custom()
                .slidingWindowSize(5)
                .minimumNumberOfCalls(5)
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(10))
                .permittedNumberOfCallsInHalfOpenState(2)
                .build();

        var registry = CircuitBreakerRegistry.of(config);

        CircuitBreaker circuitBreaker =
                registry.circuitBreaker("deck");

        // Log state transitions for our demonstration
        circuitBreaker.getEventPublisher()
                .onStateTransition(event ->
                        System.out.println(
                                "DECK CIRCUIT BREAKER: "
                                        + event.getStateTransition()
                        )
                );

        return circuitBreaker;
    }
}
