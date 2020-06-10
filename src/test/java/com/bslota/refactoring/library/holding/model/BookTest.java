package com.bslota.refactoring.library.holding.model;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.holding.fixture.BookFixture.BookBuilder.newBook;
import static com.bslota.refactoring.library.holding.fixture.BookFixture.availableBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author bslota on 19/11/2019
 */
class BookTest {

    private static final int SOME_INT_VALUE = 123;

    @Test
    void shouldCreatePatronWithGivenId() {
        //given
        BookId id = BookId.of(SOME_INT_VALUE);

        //when
        Book book = newBook().withId(id).build();

        //then
        assertEquals(id, book.getBookId());
    }

    @Test
    void shouldBePlacedOnHold() {
        //given
        Book book = availableBook();
        PatronId patronId = PatronId.of(SOME_INT_VALUE);
        int periodInDays = 100;

        //when
        book.placedOnHold(patronId, periodInDays);

        //then
        assertFalse(book.isAvailable());
    }
}
