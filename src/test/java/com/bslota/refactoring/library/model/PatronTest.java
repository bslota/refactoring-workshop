package com.bslota.refactoring.library.model;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.BookFixture.availableBook;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.patronWithMaxNumberOfHolds;
import static com.bslota.refactoring.library.fixture.PatronFixture.patronWithoutHolds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author bslota on 23/11/2019
 */
class PatronTest {

    private static final int PATRON_ID_VALUE = 10;

    @Test
    void shouldCreateWithGivenId() {
        //given
        PatronId id = PatronId.of(PATRON_ID_VALUE);

        //when
        Patron patron = newPatron().withId(id).build();

        //then
        assertEquals(id, patron.getPatronId());
    }

    @Test
    void shouldPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        patron.placeOnHold(book);

        //then
        assertEquals(1, patron.getHolds().size());
    }

    @Test
    void shouldReturnSuccessWhenBookIsPlacedOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        PlaceOnHoldResult result = patron.placeOnHold(book);

        //then
        assertTrue(result instanceof BookPlacedOnHold);
        assertEquals(patron.getPatronId(), result.patronId());
        assertEquals(book.getBookId(), result.bookId());
    }

    @Test
    void shouldReturnFailureWhenPatronHasReachedMaxNumberOfHolds() {
        //given
        Book book = availableBook();
        Patron patron = patronWithMaxNumberOfHolds();

        //when
        PlaceOnHoldResult result = patron.placeOnHold(book);

        //then
        assertTrue(result instanceof PlacingOnHoldFailed);
        assertEquals(patron.getPatronId(), result.patronId());
        assertEquals(book.getBookId(), result.bookId());
    }
}