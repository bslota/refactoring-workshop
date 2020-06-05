package com.bslota.refactoring.library.fixture;

import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.model.PatronId;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static java.util.stream.Collectors.toList;

/**
 * @author bslota on 15/11/2019
 */
public class PatronFixture {

    private static final int SOME_PATRON_ID = 10;

    public static Patron patronWithoutHolds() {
        return newPatron().build();
    }

    public static Patron patronWithMaxNumberOfHolds() {
        return newPatron()
                .withHolds(IntStream.range(0, 5).boxed().collect(toList()))
                .build();
    }

    public static Patron patronQualifyingForFreeBook() {
        return newPatron()
                .withPoints(10000)
                .build();
    }

    public static class PatronBuilder {
        private PatronId patronId = PatronId.of(SOME_PATRON_ID);
        private int type;
        private int points;
        private boolean qualifiesForFreeBook;
        private List<Integer> holds = new LinkedList<>();

        public static PatronBuilder newPatron() {
            return new PatronBuilder();
        }

        public PatronBuilder withId(PatronId id) {
            patronId = id;
            return this;
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

        public Patron build() {
            return new Patron(patronId, type, points, qualifiesForFreeBook, holds);
        }
    }
}
