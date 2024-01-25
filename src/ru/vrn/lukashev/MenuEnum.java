package ru.vrn.lukashev;

public enum MenuEnum {
    NEW_GAME("New Game"),


    ;

    private String text;

    public String getText() {
        return text;
    }

    MenuEnum(String text) {
        this.text = text;
    }
}
