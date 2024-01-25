package ru.vrn.lukashev.cards;

import java.util.ArrayList;
import java.util.List;

public enum Value {

    SIX(6, "6"),
    SEVEN(7, "7"),
    EIGHT(8, "8"),
    NINE(9, "9"),
    TEN(10, "10"),
    JACK(3, "Jack"),
    QUEEN(4, "Lady"),
    KING(5, "King"),
    ACE(11, "Ace");

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return name;
    }

    private final int weight;
    private String name;

    Value(int weight, String name){
        this.weight = weight;
        this.name = name;
    }


}
