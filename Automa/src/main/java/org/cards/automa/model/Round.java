package org.cards.automa.model;

import lombok.Getter;
import lombok.Setter;
import org.cards.automa.model.Card;

import java.util.HashMap;
import java.util.Map;

public class Round {

    private Long roundId;

    private Map<Long, Card> playerCards =  new HashMap<>();

    private Map<Long, Card> automaCards;

    private Card playerCard;

    private Card automaCard;

    private Card playerBid;

    private Card automaBid;

    private Card topic;

    @Getter@Setter
    private String outcome;

    public Round(){
        this.playerCards = new HashMap<>();
        this.automaCards = new HashMap<>();
    }

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public Map<Long, Card> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(HashMap<Long, Card> playerCards) {
        this.playerCards = playerCards;
    }

    public Map<Long, Card> getAutomaCards() {
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
