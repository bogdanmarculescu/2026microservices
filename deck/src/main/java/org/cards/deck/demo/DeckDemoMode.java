package org.cards.deck.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class DeckDemoMode {

    private final AtomicBoolean failing =
            new AtomicBoolean(false);

    public boolean isFailing() {
        return failing.get();
    }

    public void setFailing(boolean failing) {
        this.failing.set(failing);
    }
}
