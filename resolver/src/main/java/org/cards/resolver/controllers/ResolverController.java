package org.cards.resolver.controllers;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cards.resolver.model.Round;
import org.cards.resolver.services.ResolverService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/resolver")
public class ResolverController {

    private final ResolverService resolverService;

    @GetMapping
    private List<Round> getRounds() {
        List<Round> result = new ArrayList<Round>();
        result.addAll(resolverService.getRounds());
        return result;
    }

    @GetMapping("/{id}")
    private Round getRound(Long roundId) {
        Round round = resolverService.getRound(roundId);
        return round;
    }
}
