package ru.vrn.lukashev.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {

    private List<Card> deck = new ArrayList<>();

    public Deck(){
        createDeck();
    }

    public List<Card> mixDeck(){
        Collections.shuffle(deck);
        return deck;
    }

    public Card getCard(){
        return deck.remove(0);
    }

    private void createDeck(){
        for(Value value : Value.values()){
            for(Suit suit : Suit.values()){
                deck.add(new Card(suit, value));
            }
        }
    }
}
