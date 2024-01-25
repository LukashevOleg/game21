package ru.vrn.lukashev.players;

public class Bot extends Player{

    @Override
    public boolean oneMore() {

        if(getCurrentSum() < 15)
            return true;
        else if (getCurrentSum() == 16 && Math.random() > 0.5) {
            return true;
        } else if (getCurrentSum() > 16 && getCurrentSum() < 19 && Math.random() > 0.7) {
            return true;
        }
        return false;
    }
}
