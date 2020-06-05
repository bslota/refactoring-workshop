package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.Patron;
import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.regularPatron;
import static com.bslota.refactoring.library.fixture.PatronLoyaltiesFixture.randomNumberOfPoints;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bslota on 05/06/2020
 */
class PatronLoyaltiesCalculationTest {

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
        PatronLoyaltiesCalculation.addLoyaltyPointsTo(patron);

        //then
        assertEquals(points, patron.getPoints());
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
        PatronLoyaltiesCalculation.addLoyaltyPointsTo(patron);

        //then
        assertEquals(points + 1, patron.getPoints());
    }

    @Test
    void shouldAddPointsWhenPatronTypeIsResearcher() {

    }

    @Test
    void shouldSetHundredPointsWhenPatronTypeIsPremiumAndThereWereNoPointsBefore() {

    }

    @Test
    void shouldDoubleThePointsWhenPatronTypeIsPremiumAndThereWereSomePointsBefore() {

    }

    @Test
    void shouldSetQualifiesForFreeBookFlagWhenLimitIsReached() {

    }

}