package org.cards.resolver.kafka;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class TestComponent {

    @PostConstruct
    public void test() {
        System.out.println("******** TEST COMPONENT WAS CREATED ********");
    }
}
