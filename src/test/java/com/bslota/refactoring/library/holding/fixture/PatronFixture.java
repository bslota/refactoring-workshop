package com.bslota.refactoring.library.holding.fixture;

import com.bslota.refactoring.library.holding.model.Patron;
import com.bslota.refactoring.library.holding.model.PatronId;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

import static com.bslota.refactoring.library.holding.fixture.PatronFixture.PatronBuilder.newPatron;
import static java.util.stream.Collectors.toList;

/**
 * @author bslota on 15/11/2019
 */
public class PatronFixture {

    private static final int SOME_PATRON_ID = 10;
    private static final int UNKNOWN_TYPE = 111;
    private static final int INITIAL_POINT_COUNT = 0;
    private static final int REGULAR_TYPE = 0;
    private static final int RESEARCHER_TYPE = 1;
    public static final int PREMIUM_PATRON = 2;

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

        public static PatronBuilder regularPatron() {
            return newPatron().withType(REGULAR_TYPE);
        }

        public static PatronBuilder researcherPatron() {
            return newPatron().withType(RESEARCHER_TYPE);
        }

        public static PatronBuilder premiumPatron() {
            return newPatron().withType(PREMIUM_PATRON);
        }

        public static PatronBuilder newPatron() {
            return new PatronBuilder();
        }

        public PatronBuilder withId(PatronId id) {
            patronId = id;
            return this;
        }

        public PatronBuilder withType(int type) {
            this.type = type;
            return this;
        }

        public PatronBuilder withUnknownType() {
            return withType(UNKNOWN_TYPE);
        }

        public PatronBuilder withPoints(int points) {
            this.points = points;
            return this;
        }

        public PatronBuilder withoutPoints() {
            return withPoints(INITIAL_POINT_COUNT);
        }

        public PatronBuilder withRandomNumberOfPoints() {
            return withPoints(NumericFixture.randomInt());
        }

        public PatronBuilder withQualifiesForFreeBook(boolean qualifiesForFreeBook) {
            this.qualifiesForFreeBook = qualifiesForFreeBook;
            return this;
        }

        public PatronBuilder withHolds(List<Integer> holds) {
            this.holds = holds;
            return this;
        }

        public Patron build() {
            return new Patron(patronId, holds);
        }
    }

}
