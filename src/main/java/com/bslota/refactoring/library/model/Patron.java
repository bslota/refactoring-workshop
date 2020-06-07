package com.bslota.refactoring.library.model;

import com.bslota.refactoring.library.events.BookPlacedOnHold;
import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.PlacingOnHoldFailed;

import java.util.List;

public class Patron {
    private static final int MAX_NUMBER_OF_HOLDS = 5;
    private PatronId patronId;
    private List<Integer> holds;

    public Patron(PatronId patronId, List<Integer> holds) {
        this.setPatronId(patronId);
        this.holds = holds;
    }

    public DomainEvent placeOnHold(Book book) {
        if (hasNotReachedMaxNumberOfHolds() && book.isAvailable()) {
            this.holds.add(book.getBookId().asInt());
            return BookPlacedOnHold.of(this.patronId.asInt(), book.getBookId().asInt());
        }
        return PlacingOnHoldFailed.of(this.patronId.asInt(), book.getBookId().asInt());
    }

    public List<Integer> getHolds() {
        return this.holds;
    }

    public PatronId getPatronId() {
        return patronId;
    }

    public void setPatronId(PatronId patronId) {
        this.patronId = patronId;
    }

    private boolean hasNotReachedMaxNumberOfHolds() {
        return this.holds.size() < MAX_NUMBER_OF_HOLDS;
    }
}
