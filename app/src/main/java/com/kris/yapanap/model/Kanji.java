package com.kris.yapanap.model;

public class Kanji {
    private int id;
    private String symbol = "";
    private int strokeCount = 0;
    private int grade = 0;
    private int JLPT = 0;
    private String[] meanings = new String[0];
    private String[] kunReadings = new String[0];
    private String[] onReadings = new String[0];
    private String userNote = "";

    public Kanji(int id, String symbol, int strokeCount, int grade, int JLPT, String[] meanings, String[] kunReading, String[] onReading, String userNote) {
        this.id = id;
        this.symbol = symbol;
        this.strokeCount = strokeCount;
        this.grade = grade;
        this.JLPT = JLPT;
        this.meanings = meanings;
        this.kunReadings = kunReading;
        this.onReadings = onReading;
        this.userNote = userNote;
    }

    public Kanji() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public int getStrokeCount() {
        return strokeCount;
    }

    public void setStrokeCount(int strokeCount) {
        this.strokeCount = strokeCount;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getJLPT() {
        return JLPT;
    }

    public void setJLPT(int JLPT) {
        this.JLPT = JLPT;
    }

    public String[] getMeanings(){return meanings;}

    public String getMeaningsText() {
        String string = "";
        for (int i = 0; i < meanings.length; i++)
            string = string + meanings[i]+", ";
        return string;
    }

    public void setMeanings(String[] meanings) {
        this.meanings = meanings;
    }

    public String[] getKunReadings(){return kunReadings;}

    public String getKunReadingsText() {
        String string = "";
        for (int i = 0; i < kunReadings.length; i++)
            string = string + kunReadings[i]+", ";
        return string;

    }

    public void setKunReadings(String[] kunReading) {
        this.kunReadings = kunReading;
    }

    public String[] getOnReadings(){return onReadings;}

    public String getOnReadingsText() {
        String string = "";
        for (int i = 0; i < onReadings.length; i++)
            string = string + onReadings[i]+", ";
        return string;
    }

    public void setOnReadings(String[] onReading) {
        this.onReadings = onReading;
    }

    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }
}
