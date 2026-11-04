package org.cards.mono.exceptions;

public class DeckUnavailableException extends RuntimeException{
    public DeckUnavailableException(String message, Throwable cause){
        super(message, cause);
    }
}
