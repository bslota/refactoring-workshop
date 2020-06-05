package com.bslota.refactoring.library.model;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.premiumPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.regularPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.researcherPatron;
import static com.bslota.refactoring.library.fixture.PatronLoyaltiesFixture.randomNumberOfPoints;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author bslota on 05/06/2020
 */
class PatronLoyaltiesCalculationTest {

    public static final int NUMBER_OF_POINTS_QUALIFYING_FOR_FREE_BOOK = 10001;

    @Test
    void shouldNotChangeNumberOfPointsWhenPatronTypeIsUnknown() {
        //given
        int points = randomNumberOfPoints();

        //and
        Patron patron = newPatron()
                .withUnknownType()
                .withPoints(points)
                .build();

        //when
        patron.getPatronLoyalties().addLoyaltyPoints();

        //then
        assertEquals(points, patron.getPatronLoyalties().getPoints());
    }

    @Test
    void shouldAddPointsWhenPatronTypeIsRegular() {
        //given
        int points = randomNumberOfPoints();

        //and
        Patron patron = regularPatron()
                .withPoints(points)
                .build();

        //when
        patron.getPatronLoyalties().addLoyaltyPoints();

        //then
        assertEquals(points + 1, patron.getPatronLoyalties().getPoints());
    }

    @Test
    void shouldAddPointsWhenPatronTypeIsResearcher() {
        //given
        int points = randomNumberOfPoints();

        //and
        Patron patron = researcherPatron()
                .withPoints(points)
                .build();

        //when
        patron.getPatronLoyalties().addLoyaltyPoints();

        //then
        assertEquals(points + 5, patron.getPatronLoyalties().getPoints());
    }

    @Test
    void shouldSetHundredPointsWhenPatronTypeIsPremiumAndThereWereNoPointsBefore() {
        //given
        Patron patron = premiumPatron()
                .withoutPoints()
                .build();

        //when
        patron.getPatronLoyalties().addLoyaltyPoints();

        //then
        assertEquals(100, patron.getPatronLoyalties().getPoints());
    }

    @Test
    void shouldDoubleThePointsWhenPatronTypeIsPremiumAndThereWereSomePointsBefore() {
        //given
        int points = randomNumberOfPoints();

        //and
        Patron patron = premiumPatron()
                .withPoints(points)
                .build();

        //when
        patron.getPatronLoyalties().addLoyaltyPoints();

        //then
        assertEquals(points * 2, patron.getPatronLoyalties().getPoints());
    }

    @Test
    void shouldSetQualifiesForFreeBookFlagWhenLimitIsReached() {
        //given
        Patron patron = regularPatron()
                .withPoints(NUMBER_OF_POINTS_QUALIFYING_FOR_FREE_BOOK - 1)
                .build();

        //when
        patron.getPatronLoyalties().addLoyaltyPoints();

        //then
        assertTrue(patron.getPatronLoyalties().isQualifiesForFreeBook());
    }

}