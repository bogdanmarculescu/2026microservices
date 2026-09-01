package org.cards.automa.service;

import lombok.extern.slf4j.Slf4j;
import org.cards.automa.model.Card;
import org.cards.automa.model.Round;
import org.springframework.stereotype.Service;


public class AutomaPlayerImpl implements AutomaPlayer{
    @Override
    public Round automaPlay(Round round) {
        //Dummy default play

        Card playedCard = round.getAutomaCards().values().stream()
                .skip(0)
                .findFirst()
                .orElse(null);
        Card bidCard = round.getAutomaCards().values().stream()
                .skip(1)
                .findFirst()
                .orElse(null);
        System.out.println("Automa Played Card: "+ playedCard + "\n");
        System.out.println("Automa Bid Card: " + bidCard + "\n");

        round.setAutomaCard(playedCard);
        round.setAutomaBid(bidCard);

        System.out.println(round);

        return round;
    }
}
