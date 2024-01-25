package ru.vrn.lukashev.players;

import ru.vrn.lukashev.cards.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private List<Card> listCard = new ArrayList<>();
    private int currentSum = 0;

    public List<Card> getListCard() {
        return listCard;
    }
    public int getCurrentSum() {
        return currentSum;
    }

    public void addCard(Card card){
        listCard.add(card);
        currentSum += card.getValue().getWeight();
    }

    public void returnCards(){
        listCard = new ArrayList<>();
        currentSum = 0;
    }

    public boolean oneMore(){
        return false;
    }


}
