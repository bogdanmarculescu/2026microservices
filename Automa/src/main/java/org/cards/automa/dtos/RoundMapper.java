package org.cards.automa.dtos;

import org.cards.automa.model.Card;
import org.cards.automa.model.Round;
import org.springframework.stereotype.Component;

@Component
public class RoundMapper {

    private final CardMapper cardMapper;

    public RoundMapper(CardMapper cardMapper) {
        this.cardMapper = cardMapper;
    }

    public RoundDTO toRoundDTO(Round round){
        RoundDTO roundDTO = new RoundDTO();

        roundDTO.setRoundId(round.getRoundId());

        if(round.getTopic() != null){
            roundDTO.setTopic(cardMapper.toDTO(round.getTopic()));
        }

        //Player Cards
        if(round.getPlayerCard() != null){
            roundDTO.setPlayerCard(cardMapper.toDTO(round.getPlayerCard()));
        }
        if(round.getPlayerBid() != null){
            roundDTO.setPlayerBid(cardMapper.toDTO(round.getPlayerBid()));
        }

        if(round.getPlayerCards() != null){
            Long pIndex = 1L;
            for(Card c : round.getPlayerCards().values()){
                roundDTO.getPlayerCards().put(pIndex, cardMapper.toDTO(c));
                pIndex++;
            }
        }


        //Automa Cards
        if(round.getAutomaCard() != null){
            roundDTO.setAutomaCard(cardMapper.toDTO(round.getAutomaCard()));
        }
        if(round.getAutomaBid() != null){
            roundDTO.setAutomaBid(cardMapper.toDTO(round.getAutomaBid()));
        }
        if(round.getAutomaCards() != null) {
            Long aIndex = 1L;
            for (Card c : round.getAutomaCards().values()) {
                roundDTO.getAutomaCards().put(aIndex, cardMapper.toDTO(c));
                aIndex++;
            }
        }
        return roundDTO;
    }

    public Round fromDTO(RoundDTO roundDTO){
        Round round = new Round();
        round.setRoundId(roundDTO.getRoundId());
        if(roundDTO.getTopic() != null){
            round.setTopic(cardMapper.fromDTO(roundDTO.getTopic()));
        }

        //Player Cards
        if(roundDTO.getPlayerCard() != null){
            round.setPlayerCard(cardMapper.fromDTO(roundDTO.getPlayerCard()));
        }
        if(roundDTO.getPlayerBid() != null){
            round.setPlayerBid(cardMapper.fromDTO(roundDTO.getPlayerBid()));
        }
        if(roundDTO.getPlayerCards() != null){
            Long pIndex = 1L;
            for(CardDTO c : roundDTO.getPlayerCards().values()){
                round.getPlayerCards().put(pIndex, cardMapper.fromDTO(c));
                pIndex++;
            }
        }

        //Automa Cards
        if(roundDTO.getAutomaCard() != null){
            round.setAutomaCard(cardMapper.fromDTO(roundDTO.getAutomaCard()));
        }
        if(roundDTO.getAutomaBid() != null){
            round.setAutomaBid(cardMapper.fromDTO(roundDTO.getAutomaBid()));
        }
        if(roundDTO.getAutomaCards() != null){
            Long aIndex = 1L;
            for(CardDTO c : roundDTO.getAutomaCards().values()){
                round.getAutomaCards().put(aIndex, cardMapper.fromDTO(c));
                aIndex++;
            }
        }
        return round;
    }

}
