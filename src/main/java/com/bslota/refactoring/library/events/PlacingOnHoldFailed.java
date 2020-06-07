package com.bslota.refactoring.library.events;

/**
 * @author bslota on 07/06/2020
 */
public final class PlacingOnHoldFailed extends BaseDomainEvent {

    private final int patronId;
    private final int bookId;

    private PlacingOnHoldFailed(int patronId, int bookId) {
        super(String.valueOf(patronId));
        this.patronId = patronId;
        this.bookId = bookId;
    }

    public static PlacingOnHoldFailed of(int patronId, int bookId) {
        return new PlacingOnHoldFailed(patronId, bookId);
    }

    public int getPatronId() {
        return patronId;
    }

    public int getBookId() {
        return bookId;
    }
}
