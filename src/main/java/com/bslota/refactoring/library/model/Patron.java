package com.bslota.refactoring.library.model;

import java.util.List;

public class Patron {
    private PatronId patronId;
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;
    private List<Integer> holds;

    public Patron(PatronId patronId, int type, int points, boolean qualifiesForFreeBook, List<Integer> holds) {
        this.setPatronId(patronId);
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
        this.holds = holds;
    }

    public void placeOnHold(Book book) {
        this.holds.add(book.getBookId().asInt());
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

    public PatronId getPatronId() {
        return patronId;
    }

    public void setPatronId(PatronId patronId) {
        this.patronId = patronId;
    }
}
