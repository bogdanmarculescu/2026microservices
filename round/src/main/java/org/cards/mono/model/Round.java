package org.cards.mono.model;

import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.cards.mono.dtos.RoundDTO;

import java.util.HashMap;
import java.util.Map;

@Entity
public class Round {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long roundId;

    @ManyToMany(cascade = CascadeType.ALL)
    @MapKey(name = "id")
    private Map<Long, Card> playerCards =  new HashMap<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @MapKey(name = "id")
    private Map<Long, Card> automaCards;

    @ManyToOne(cascade = CascadeType.ALL)
    private Card playerCard;

    @ManyToOne(cascade = CascadeType.ALL)
    private Card automaCard;

    @ManyToOne(cascade = CascadeType.ALL)
    private Card playerBid;

    @ManyToOne(cascade = CascadeType.ALL)
    private Card automaBid;

    @ManyToOne(cascade = CascadeType.ALL)
    private Card topic;

    @Getter@Setter
    private int outcome;

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
