package com.bslota.refactoring.library.events;

/**
 * @author bslota on 07/06/2020
 */
public final class BookPlacedOnHold extends BaseDomainEvent {

    private final int patronId;
    private final int bookId;

    private BookPlacedOnHold(int patronId, int bookId) {
        super(String.valueOf(patronId));
        this.patronId = patronId;
        this.bookId = bookId;
    }

    public static BookPlacedOnHold of(int patronId, int bookId) {
        return new BookPlacedOnHold(patronId, bookId);
    }

    public int getPatronId() {
        return patronId;
    }

    public int getBookId() {
        return bookId;
    }
}
