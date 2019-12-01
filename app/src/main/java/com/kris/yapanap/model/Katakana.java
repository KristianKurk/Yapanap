package com.kris.yapanap.model;

public class Katakana {
    private int id;
    private String name;
    private String symbol;
    private String column;
    private String userNote;

    public Katakana(String name, String symbol, String column, String userNote) {
        this.name = name;
        this.symbol = symbol;
        this.column = column;
        this.userNote = userNote;
    }

    public Katakana(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
