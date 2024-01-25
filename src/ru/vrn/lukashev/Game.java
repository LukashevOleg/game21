package ru.vrn.lukashev;

import ru.vrn.lukashev.cards.Value;
import ru.vrn.lukashev.players.Bot;
import ru.vrn.lukashev.players.Player;

public class Game {

    private GameStatus gameStatus = GameStatus.WAITING;
    private Desk desk;
    private WinnerEnum winner;
    private Player gamer = new Player();
    private Player bot = new Bot();
    private boolean gameStatusIsChanged = false;

    public Game() {
        this.gameStatus = GameStatus.MENU;
    }

    public boolean gameStatusChanged(){
        if(gameStatusIsChanged){
            gameStatusIsChanged = false;
            return true;
        }
        return false;
    }

    public void newGame(){
        setGameStatus(GameStatus.NEW_GAME);
        gamer.returnCards();
        bot.returnCards();
        gameProcess();
    }

    public void gamerGetsTwoCard(){
        setGameStatus(GameStatus.PLAYER_GET_TWO_CARD);
        gameProcess();
    }

    public void backToMenu(){
        setGameStatus(GameStatus.MENU);
    }


    public void botThinkToTakeCard(){
        if (bot.oneMore())
            setGameStatus(GameStatus.BOT_TAKE_CARDS);
        else
            setGameStatus(GameStatus.CHECK_RESULT);
        gameProcess();
    }

    public void giveCardToGamer(){
        setGameStatus(GameStatus.PLAYER_TAKE_CARDS);
        gameProcess();
    }

    public void enoughToGamer(){
        setGameStatus(GameStatus.BOT_GET_TWO_CARD);
        gameProcess();
    }

    private void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
        this.gameStatusIsChanged = true;
    }

    private void gameProcess(){

        switch (gameStatus){
            case NEW_GAME -> {
                desk = new Desk();
                desk.getDeck().mixDeck();
            }
            case PLAYER_GET_TWO_CARD -> {
                gamer.addCard(desk.getDeck().getCard());
                gamer.addCard(desk.getDeck().getCard());
                checkWinner();
            }
            case PLAYER_TAKE_CARDS -> {
                gamer.addCard(desk.getDeck().getCard());
                checkWinner();
            }
            case BOT_GET_TWO_CARD -> {
                bot.addCard(desk.getDeck().getCard());
                bot.addCard(desk.getDeck().getCard());
                checkWinner();
                setGameStatus(GameStatus.BOT_TAKE_CARDS);
            }
            case BOT_TAKE_CARDS -> {
                bot.addCard(desk.getDeck().getCard());
                checkWinner();
            }
            case CHECK_RESULT -> {
                checkWinner();
            }
            case ENDGAME -> {
                gamer.returnCards();
            }
        }
    }

    private void checkWinner() {
        if(     bot.getCurrentSum() > 21 ||
                gamer.getCurrentSum() == 21 ||
                gamer.getListCard().stream().allMatch(card -> card.getValue() == Value.ACE) ||
               (gamer.getCurrentSum() > bot.getCurrentSum() && gameStatus == GameStatus.CHECK_RESULT)
        ){
            setGameStatus(GameStatus.ENDGAME);
            winner = WinnerEnum.GAMER;
        } else if (gamer.getCurrentSum() > 21 ||
                (gamer.getCurrentSum() < bot.getCurrentSum() && gameStatus == GameStatus.CHECK_RESULT) ||
                bot.getCurrentSum() == 21 ||
                (bot.getListCard().stream().allMatch(card -> card.getValue() == Value.ACE)) && !bot.getListCard().isEmpty()) {

            setGameStatus(GameStatus.ENDGAME);
            winner = WinnerEnum.BOT;

        } else if (gamer.getCurrentSum() == bot.getCurrentSum() && gameStatus == GameStatus.CHECK_RESULT) {

            setGameStatus(GameStatus.ENDGAME);
            winner = WinnerEnum.DRAW;
        }
    }

    public WinnerEnum getWinner() {
        return winner;
    }
    public Player getGamer() {
        return gamer;
    }
    public Player getBot() {
        return bot;
    }
    public GameStatus getGameStatus() {
        return gameStatus;
    }
}


