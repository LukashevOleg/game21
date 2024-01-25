package ru.vrn.lukashev.cards;

import java.io.File;

public class Card {

    private Suit suit;
    private Value value;
    private String id;

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit=" + suit +
                ", value=" + value +
                '}';
    }

    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        StringBuilder path = new StringBuilder(100);
        String firstPart = new File("").getAbsolutePath() + "/cardsPicture/";
        String lastPart = ".png";
        path.append(firstPart).append(value.getName()).append(suit.getName()).append(lastPart);
        id = path.toString();
    }

    public String getId() {
        return id;
    }
}
