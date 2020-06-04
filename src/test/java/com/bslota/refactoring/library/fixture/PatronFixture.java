package com.bslota.refactoring.library.fixture;

import com.bslota.refactoring.library.model.Patron;

import java.util.LinkedList;
import java.util.List;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;

/**
 * @author bslota on 15/11/2019
 */
class PatronFixture {

    private static final int SOME_PATRON_ID = 10;

    static Patron patronWithoutHolds() {
        return newPatron().build();
    }

    static class PatronBuilder {
        private int patronId = SOME_PATRON_ID;
        private int type;
        private int points;
        private boolean qualifiesForFreeBook;
        private List<Integer> holds = new LinkedList<>();

        static PatronBuilder newPatron() {
            return new PatronBuilder();
        }

        Patron build() {
            return new Patron(patronId, type, points, qualifiesForFreeBook, holds);
        }
    }
}
