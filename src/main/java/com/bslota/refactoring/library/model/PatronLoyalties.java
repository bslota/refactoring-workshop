package com.bslota.refactoring.library.model;

public class PatronLoyalties {
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;

    public PatronLoyalties() {
    }

    PatronLoyalties(int type, int points, boolean qualifiesForFreeBook) {
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
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