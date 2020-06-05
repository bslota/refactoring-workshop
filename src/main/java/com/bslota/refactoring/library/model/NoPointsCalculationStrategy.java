package com.bslota.refactoring.library.model;

/**
 * @author bslota on 12/01/2020
 */
class NoPointsCalculationStrategy implements PointCalculationStrategy {

    public static final int NUMBER_OF_POINTS_TO_RETURN = 0;

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() + NUMBER_OF_POINTS_TO_RETURN;
    }
}
