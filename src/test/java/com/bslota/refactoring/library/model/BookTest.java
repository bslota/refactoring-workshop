package com.bslota.refactoring.library.model;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.BookFixture.BookBuilder.newBook;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
