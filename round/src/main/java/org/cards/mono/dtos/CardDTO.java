package org.cards.mono.dtos;

import org.cards.mono.model.Suite;

public class CardDTO {
    private Long id;
    private int cardValue;
    private Suite suite;
    private String filename;

    public CardDTO() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getCardValue() {
        return cardValue;
    }
    public void setCardValue(int cardValue) {
        this.cardValue = cardValue;
    }
    public Suite getSuite() {
        return suite;
    }
    public void setSuite(Suite suite) {
        this.suite = suite;
    }
    public String getFilename() {
        return filename;
    }
    public void setFilename(String filename) {
        this.filename = filename;
    }
}