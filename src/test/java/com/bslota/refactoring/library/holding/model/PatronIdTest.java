package com.bslota.refactoring.library.holding.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author bslota on 05/06/2020
 */
class PatronIdTest {

    public static final int SOME_VALUE = 10;

    @Test
    void shouldBeCreatedFromInt() {
        //when
        PatronId id = PatronId.of(SOME_VALUE);

        //then
        assertNotNull(id);
    }

    @Test
    void shouldBeConvertedToInt() {
        //when
        PatronId id = PatronId.of(SOME_VALUE);

        //then
        assertEquals(SOME_VALUE, id.asInt());
    }

    @Test
    void twoPatronIdsWithTheSameValueShouldBeEqual() {
        //given
        PatronId firstId = PatronId.of(SOME_VALUE);
        PatronId secondID = PatronId.of(SOME_VALUE);

        //expect
        assertEquals(firstId, secondID);
    }
}
