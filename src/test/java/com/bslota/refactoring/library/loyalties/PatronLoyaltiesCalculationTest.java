package com.bslota.refactoring.library.loyalties;

import org.junit.jupiter.api.Test;

import static com.bslota.refactoring.library.loyalties.PatronLoyaltiesFixture.PatronLoyaltiesBuilder.patronLoyalties;
import static com.bslota.refactoring.library.loyalties.PatronLoyaltiesFixture.randomNumberOfPoints;
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
        PatronLoyalties patronLoyalties = patronLoyalties()
                .withValueOf(points)
                .forPatronOfUnknownType()
                .build();

        //when
        patronLoyalties.addLoyaltyPoints();

        //then
        assertEquals(points, patronLoyalties.getPoints());
    }

    @Test
    void shouldAddPointsWhenPatronTypeIsRegular() {
        //given
        int points = randomNumberOfPoints();

        //and
        PatronLoyalties patronLoyalties = patronLoyalties()
                .withValueOf(points)
                .forRegularPatron()
                .build();

        //when
        patronLoyalties.addLoyaltyPoints();

        //then
        assertEquals(points + 1, patronLoyalties.getPoints());
    }

    @Test
    void shouldAddPointsWhenPatronTypeIsResearcher() {
        //given
        int points = randomNumberOfPoints();

        //and
        PatronLoyalties patronLoyalties = patronLoyalties()
                .withValueOf(points)
                .forResearcherPatron()
                .build();

        //when
        patronLoyalties.addLoyaltyPoints();

        //then
        assertEquals(points + 5, patronLoyalties.getPoints());
    }

    @Test
    void shouldSetHundredPointsWhenPatronTypeIsPremiumAndThereWereNoPointsBefore() {
        //given
        PatronLoyalties patronLoyalties = patronLoyalties()
                .empty()
                .forPremiumPatron()
                .build();

        //when
        patronLoyalties.addLoyaltyPoints();

        //then
        assertEquals(100, patronLoyalties.getPoints());
    }

    @Test
    void shouldDoubleThePointsWhenPatronTypeIsPremiumAndThereWereSomePointsBefore() {
        //given
        int points = randomNumberOfPoints();

        //and
        PatronLoyalties patronLoyalties = patronLoyalties()
                .withValueOf(points)
                .forPremiumPatron()
                .build();

        //when
        patronLoyalties.addLoyaltyPoints();

        //then
        assertEquals(points * 2, patronLoyalties.getPoints());
    }

    @Test
    void shouldSetQualifiesForFreeBookFlagWhenLimitIsReached() {
        //given
        PatronLoyalties patronLoyalties = patronLoyalties()
                .withValueOf(NUMBER_OF_POINTS_QUALIFYING_FOR_FREE_BOOK - 1)
                .forRegularPatron()
                .build();

        //when
        patronLoyalties.addLoyaltyPoints();

        //then
        assertTrue(patronLoyalties.isQualifiesForFreeBook());
    }

}