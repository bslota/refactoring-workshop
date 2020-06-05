package com.bslota.refactoring.library.model;

/**
 * @author bslota on 05/06/2020
 */
class PlacingOnHoldFailed implements PlaceOnHoldResult {

    private final BookId bookId;
    private final PatronId patronId;

    private PlacingOnHoldFailed(BookId bookId, PatronId patronId) {
        this.patronId = patronId;
        this.bookId = bookId;
    }

    static PlaceOnHoldResult of(BookId bookId, PatronId patronId) {
        return new PlacingOnHoldFailed(bookId, patronId);
    }

    @Override
    public PatronId patronId() {
        return patronId;
    }

    @Override
    public BookId bookId() {
        return bookId;
    }
}
