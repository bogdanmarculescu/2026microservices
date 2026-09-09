package org.cards.mono.dtos;

import org.cards.mono.model.Card;

import jakarta.persistence.*;

import java.util.HashMap;


public class RoundDTO {


    private Long roundId;

    private HashMap<Long, Card> automaCards;
    private HashMap<Long, Card> playerCards;


    private Card automaCard;
    private Card automaBid;

    private Card playerCard;
    private Card playerBid;

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public HashMap<Long, Card> getAutomaCards() {
        return automaCards;
    }

    public void setAutomaCards(HashMap<Long, Card> automaCards) {
        this.automaCards = automaCards;
    }

    public HashMap<Long, Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(HashMap<Long, Card> playerCards) {
        this.playerCards = playerCards;
    }

    public Card getAutomaCard() {
        return automaCard;
    }

    public void setAutomaCard(Card automaCard) {
        this.automaCard = automaCard;
    }

    public Card getAutomaBid() {
        return automaBid;
    }

    public void setAutomaBid(Card automaBid) {
        this.automaBid = automaBid;
    }

    public Card getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(Card playerCard) {
        this.playerCard = playerCard;
    }

    public Card getPlayerBid() {
        return playerBid;
    }

    public void setPlayerBid(Card playerBid) {
        this.playerBid = playerBid;
    }
}
