package com.bslota.refactoring.library.model;

/**
 * @author bslota on 05/06/2020
 */
class BookPlacedOnHold implements PlaceOnHoldResult {

    private final BookId bookId;
    private final PatronId patronId;

    private BookPlacedOnHold(BookId bookId, PatronId patronId) {
        this.patronId = patronId;
        this.bookId = bookId;
    }

    static PlaceOnHoldResult of(BookId bookId, PatronId patronId) {
        return new BookPlacedOnHold(bookId, patronId);
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
