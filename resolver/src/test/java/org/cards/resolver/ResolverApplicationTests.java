package org.cards.resolver;

import org.cards.resolver.model.Round;
import org.cards.resolver.model.RoundRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.shaded.org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class ResolverApplicationTests {

    @Autowired
    private RoundRepository roundRepository;

    @Test
    void contextLoads() {
    }

    @Test
    void addRound() {
        Round round = new Round();
        round.setRoundId(42L);

        List<Round> before = new ArrayList<Round>();
        roundRepository.findAll().forEach(before::add);

        assertEquals(0, before.size());

        roundRepository.save(round);

        List<Round> after = new ArrayList<Round>();
        roundRepository.findAll().forEach(after::add);

        assertEquals(1, after.size());

    }

}
