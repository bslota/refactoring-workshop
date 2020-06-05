package com.bslota.refactoring.library.model;

public class PatronLoyalties {
    private int patronId;
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;

    public PatronLoyalties(int patronId, int type, int points, boolean qualifiesForFreeBook) {
        this.patronId = patronId;
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
    }

    int getPatronId() {
        return patronId;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getPoints() {
        return this.points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setQualifiesForFreeBook(boolean flag) {
        this.qualifiesForFreeBook = flag;
    }

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }
}