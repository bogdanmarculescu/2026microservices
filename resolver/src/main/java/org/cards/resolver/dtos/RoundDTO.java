package org.cards.resolver.dtos;

import org.cards.resolver.dtos.CardDTO;

import java.util.HashMap;


public class RoundDTO {

    private Long roundId;
    private CardDTO topic;

    private HashMap<Long, CardDTO> automaCards =  new HashMap<Long, CardDTO>();
    private HashMap<Long, CardDTO> playerCards =  new HashMap<Long, CardDTO>();

    private CardDTO automaCard;
    private CardDTO automaBid;

    private CardDTO playerCard;
    private CardDTO playerBid;

    public RoundDTO(){}

    public Long getRoundId() {
        return roundId;
    }

    public void setRoundId(Long roundId) {
        this.roundId = roundId;
    }

    public HashMap<Long, CardDTO> getAutomaCards() {
        return automaCards;
    }

    public void setAutomaCards(HashMap<Long, CardDTO> automaCards) {
        this.automaCards = automaCards;
    }

    public HashMap<Long, CardDTO> getPlayerCards() {
        return playerCards;
    }

    public void setPlayerCards(HashMap<Long, CardDTO> playerCards) {
        this.playerCards = playerCards;
    }

    public CardDTO getAutomaCard() {
        return automaCard;
    }

    public void setAutomaCard(CardDTO automaCard) {
        this.automaCard = automaCard;
    }

    public CardDTO getAutomaBid() {
        return automaBid;
    }

    public void setAutomaBid(CardDTO automaBid) {
        this.automaBid = automaBid;
    }

    public CardDTO getPlayerCard() {
        return playerCard;
    }

    public void setPlayerCard(CardDTO playerCard) {
        this.playerCard = playerCard;
    }

    public CardDTO getPlayerBid() {
        return playerBid;
    }

    public void setPlayerBid(CardDTO playerBid) {
        this.playerBid = playerBid;
    }

    public CardDTO getTopic() {
        return topic;
    }

    public void setTopic(CardDTO topic) {
        this.topic = topic;
    }
}