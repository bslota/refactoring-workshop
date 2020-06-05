package com.bslota.refactoring.library.model;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.BookFixture.availableBook;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.patronWithoutHolds;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    }

    @Test
    void shouldReturnFailureWhenPatronHasReachedMaxNumberOfHolds() {
    }
}