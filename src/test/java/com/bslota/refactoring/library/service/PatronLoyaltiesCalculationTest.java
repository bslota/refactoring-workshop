package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.Patron;
import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.newPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.premiumPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.regularPatron;
import static com.bslota.refactoring.library.fixture.PatronFixture.PatronBuilder.researcherPatron;
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
        //given
        int points = randomNumberOfPoints();

        //and
        Patron patron = researcherPatron()
                .withPoints(points)
                .build();

        //when
        PatronLoyaltiesCalculation.addLoyaltyPointsTo(patron);

        //then
        assertEquals(points + 5, patron.getPoints());
    }

    @Test
    void shouldSetHundredPointsWhenPatronTypeIsPremiumAndThereWereNoPointsBefore() {
        //given
        Patron patron = premiumPatron()
                .withoutPoints()
                .build();

        //when
        PatronLoyaltiesCalculation.addLoyaltyPointsTo(patron);

        //then
        assertEquals(100, patron.getPoints());
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
        PatronLoyaltiesCalculation.addLoyaltyPointsTo(patron);

        //then
        assertEquals(points * 2, patron.getPoints());
    }

    @Test
    void shouldSetQualifiesForFreeBookFlagWhenLimitIsReached() {

    }

}