package com.bslota.refactoring.library.model;

import java.util.List;

public class Patron {
    public static final int MAX_NUMBER_OF_HOLDS = 5;
    private final PatronLoyalties patronLoyalties;
    private PatronId patronId;
    private List<Integer> holds;

    public Patron(PatronId patronId, int type, int points, boolean qualifiesForFreeBook, List<Integer> holds) {
        this.setPatronId(patronId);
        this.patronLoyalties = new PatronLoyalties(type, points, qualifiesForFreeBook);
        this.holds = holds;
    }

    public PlaceOnHoldResult placeOnHold(Book book) {
        if (hasNotReachedMaxNumberOfHolds() && book.isAvailable()) {
            this.holds.add(book.getBookId().asInt());
            return BookPlacedOnHold.of(book.getBookId(), this.patronId);
        }
        return PlacingOnHoldFailed.of(book.getBookId(), this.patronId);
    }

    public int getType() {
        return getPatronLoyalties().getType();
    }

    public void setType(int type) {
        getPatronLoyalties().setType(type);
    }

    public int getPoints() {
        return getPatronLoyalties().getPoints();
    }

    public void setPoints(int points) {
        getPatronLoyalties().setPoints(points);
    }

    public void setQualifiesForFreeBook(boolean flag) {
        getPatronLoyalties().setQualifiesForFreeBook(flag);
    }

    public boolean isQualifiesForFreeBook() {
        return getPatronLoyalties().isQualifiesForFreeBook();
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

    public boolean hasNotReachedMaxNumberOfHolds() {
        return this.holds.size() < MAX_NUMBER_OF_HOLDS;
    }

    public PatronLoyalties getPatronLoyalties() {
        return patronLoyalties;
    }
}
