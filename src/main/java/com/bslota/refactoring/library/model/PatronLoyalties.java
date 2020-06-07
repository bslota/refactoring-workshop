package com.bslota.refactoring.library.model;

public class PatronLoyalties {

    private static final int DEFAULT_POINTS_COUNT = 0;
    private static final boolean FREE_BOOK_BY_DEFAULT = false;
    private static final int DEFAULT_PATRON_TYPE = 0;

    private PatronId patronId;
    private int type;
    private int points;
    private boolean qualifiesForFreeBook;
    private PointCalculationStrategy pointCalculationStrategy;

    public PatronLoyalties(PatronId patronId, int type, int points, boolean qualifiesForFreeBook) {
        this.patronId = patronId;
        this.type = type;
        this.points = points;
        this.qualifiesForFreeBook = qualifiesForFreeBook;
        this.pointCalculationStrategy = PointCalculationStrategy.from(type);
    }

    public static PatronLoyalties emptyFor(PatronId patronId) {
        return new PatronLoyalties(patronId, DEFAULT_PATRON_TYPE, DEFAULT_POINTS_COUNT, FREE_BOOK_BY_DEFAULT);
    }

    public PatronId getPatronId() {
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

    public void setQualifiesForFreeBook(boolean flag) {
        this.qualifiesForFreeBook = flag;
    }

    public boolean isQualifiesForFreeBook() {
        return this.qualifiesForFreeBook;
    }

    public void addLoyaltyPoints() {
        this.points = pointCalculationStrategy.calculate(this);
        if (this.points > 10000) {
            setQualifiesForFreeBook(true);
        }
    }
}