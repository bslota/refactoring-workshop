package com.bslota.refactoring.library.fixture;

import com.bslota.refactoring.library.model.PatronId;
import com.bslota.refactoring.library.model.PatronLoyalties;

import static com.bslota.refactoring.library.fixture.NumericFixture.randomInt;

/**
 * @author bslota on 05/06/2020
 */
public class PatronLoyaltiesFixture {

    private static final int UNKNOWN_PATRON_TYPE = 111;
    private static final int REGULAR_PATRON_TYPE = 0;
    private static final int RESEARCHER_PATRON_TYPE = 1;
    private static final int PREMIUM_PATRON_TYPE = 2;
    private static final int INITIAL_NUMBER_OF_POINTS = 0;
    private static final int NUMBER_OF_POINTS_QUALIFYING_FOR_FREE_BOOK = 10001;

    public static int randomNumberOfPoints() {
        return randomInt();
    }

    public static class PatronLoyaltiesBuilder {
        private PatronId patronId = PatronId.of(randomInt());
        private int type;
        private int points;
        private boolean qualifiesForFreeBook;

        public static PatronLoyaltiesBuilder patronLoyalties() {
            return new PatronLoyaltiesBuilder();
        }

        public PatronLoyaltiesBuilder empty() {
            return withValueOf(INITIAL_NUMBER_OF_POINTS);
        }

        public PatronLoyaltiesBuilder withValueOf(int points) {
            this.points = points;
            return this;
        }

        public PatronLoyaltiesBuilder withValueQualifyingForFreeBook() {
            return withValueOf(NUMBER_OF_POINTS_QUALIFYING_FOR_FREE_BOOK);
        }

        public PatronLoyaltiesBuilder forPatronOfUnknownType() {
            return withPatronType(UNKNOWN_PATRON_TYPE);
        }

        public PatronLoyaltiesBuilder forRegularPatron() {
            return withPatronType(REGULAR_PATRON_TYPE);
        }

        public PatronLoyaltiesBuilder forResearcherPatron() {
            return withPatronType(RESEARCHER_PATRON_TYPE);
        }

        public PatronLoyaltiesBuilder forPremiumPatron() {
            return withPatronType(PREMIUM_PATRON_TYPE);
        }

        public PatronLoyaltiesBuilder withPatronType(int type) {
            this.type = type;
            return this;
        }

        public PatronLoyalties build() {
            return new PatronLoyalties(patronId, type, points, qualifiesForFreeBook);
        }
    }
}
