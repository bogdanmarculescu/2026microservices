package org.cards.mono.model;

import java.util.HashMap;

public class Round {
    private Long id;
    private HashMap<Long, Card> playerCards;
    private HashMap<Long, Card> automaCards;

    private Card playerCard;
    private Card automaCard;

    private Card playerBid;
    private Card automaBid;

    private Card topic;

    public Round(){
        this.playerCards = new HashMap<>();
        this.automaCards = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<Long, Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(HashMap<Long, Card> playerCards) {
        this.playerCards = playerCards;
    }

    public HashMap<Long, Card> getAutomaCards() {
        return automaCards;
    }

    public void setAutomaCards(HashMap<Long, Card> automaCards) {
        this.automaCards = automaCards;
    }

    public Card getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(Card playerCard) {
        this.playerCard = playerCard;
    }

    public Card getAutomaCard() {
        return automaCard;
    }

    public void setAutomaCard(Card automaCard) {
        this.automaCard = automaCard;
    }

    public Card getPlayerBid() {
        return playerBid;
    }

    public void setPlayerBid(Card playerBid) {
        this.playerBid = playerBid;
    }

    public Card getAutomaBid() {
        return automaBid;
    }

    public void setAutomaBid(Card automaBid) {
        this.automaBid = automaBid;
    }

    public Card getTopic() {
        return topic;
    }

    public void setTopic(Card topic) {
        this.topic = topic;
    }
}
