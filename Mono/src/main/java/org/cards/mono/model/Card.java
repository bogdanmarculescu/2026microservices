package org.cards.mono.model;

public class Card {
    private Long id;
    private int value;
    private Suite suite;
    private String filename;

    public Card(Long id, int value, Suite suite, String filename) {
        this.id = id;
        this.value = value;
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

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
