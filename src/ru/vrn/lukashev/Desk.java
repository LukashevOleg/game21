package ru.vrn.lukashev;

import ru.vrn.lukashev.cards.Card;
import ru.vrn.lukashev.cards.Deck;

import java.util.ArrayList;
import java.util.List;

public class Desk{

    private Deck deck;
    private List<Card> listCard;

    public void clear(){
        listCard = new ArrayList<>();
        deck =  new Deck();
    }

    public Desk() {
        this.deck = new Deck();
        this.listCard = new ArrayList<>();
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public List<Card> getListCard() {
        return listCard;
    }

    public void setListCard(List<Card> listCard) {
        this.listCard = listCard;
    }




}
