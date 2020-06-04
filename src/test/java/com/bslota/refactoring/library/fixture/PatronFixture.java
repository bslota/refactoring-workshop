package com.bslota.refactoring.library.fixture;

import com.bslota.refactoring.library.model.Patron;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static java.util.stream.Collectors.toList;

/**
 * @author bslota on 15/11/2019
 */
class PatronFixture {

    private static final int SOME_PATRON_ID = 10;

    static Patron patronWithoutHolds() {
        return newPatron().build();
    }

    static Patron patronWithMaxNumberOfHolds() {
        return newPatron()
                .withHolds(IntStream.range(0, 5).boxed().collect(toList()))
                .build();
    }

    static Patron patronQualifyingForFreeBook() {
        return newPatron()
                .withPoints(10000)
                .build();
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

        PatronBuilder withType(int type) {
            this.type = type;
            return this;
        }

        PatronBuilder withPoints(int points) {
            this.points = points;
            return this;
        }

        PatronBuilder withQualifiesForFreeBook(boolean qualifiesForFreeBook) {
            this.qualifiesForFreeBook = qualifiesForFreeBook;
            return this;
        }

        PatronBuilder withHolds(List<Integer> holds) {
            this.holds = holds;
            return this;
        }

        Patron build() {
            return new Patron(patronId, type, points, qualifiesForFreeBook, holds);
        }
    }
}
