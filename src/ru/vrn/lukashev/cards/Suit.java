package ru.vrn.lukashev.cards;

public enum Suit {
    HEARTS("Cher"),
    SPADES("Pick"),
    DIAMONDS("Bub"),
    CLUBS("Kres");

    private String name;

    Suit(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
