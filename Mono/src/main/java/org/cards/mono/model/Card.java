package org.cards.mono.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Card {
    @Id
    private Long id;
    private int cardValue;
    private Suite suite;
    private String filename;

    public Card(Long id, int cardValue, Suite suite, String filename) {
        this.id = id;
        this.cardValue = cardValue;
        this.suite = suite;
        this.filename = filename;
    }

    public Card(){
    }

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
