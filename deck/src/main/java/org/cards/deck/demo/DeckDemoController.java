package org.cards.deck.demo;

import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/deck/demo")
@Profile("demo")
public class DeckDemoController {

    private final DeckDemoMode demoMode;

    public DeckDemoController(DeckDemoMode demoMode) {
        this.demoMode = demoMode;
    }

    @PostMapping("/failure")
    public String setFailure(@RequestParam boolean enabled) {

        demoMode.setFailing(enabled);

        return "Deck failure mode: " + enabled;
    }
}