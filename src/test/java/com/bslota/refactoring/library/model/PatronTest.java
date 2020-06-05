package com.bslota.refactoring.library.model;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
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

}