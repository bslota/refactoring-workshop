package com.bslota.refactoring.library.model;

import java.util.List;

public class Patron {
    private int patronId;
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;
    private List<Integer> holds;

    public Patron(int patronId, int type, int points, boolean qualifiesForFreeBook, List<Integer> holds) {
        this.patronId = patronId;
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
        this.holds = holds;
    }

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
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

    public List<Integer> getHolds() {
        return this.holds;
    }

    public void setHolds(List<Integer> holds) {
        this.holds = holds;
    }
}
