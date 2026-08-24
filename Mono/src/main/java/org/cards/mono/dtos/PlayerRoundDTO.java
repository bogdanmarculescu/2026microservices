package org.cards.mono.dtos;

import org.cards.mono.model.Card;

public class PlayerRoundDTO {
    private Long roundId;

    private Card playedCard;
    private Card bidCard;

    public Card getPlayedCard() { return playedCard; }
    public Card getBidCard() { return bidCard; }
    public Long getRoundId() { return roundId; }


    public void setPlayedCard(Card playedCard) {this.playedCard = playedCard;}
    public void setBidCard(Card bidCard) {this.bidCard = bidCard;}
    public void setRoundId(Long roundId) { this.roundId = roundId; }
}
