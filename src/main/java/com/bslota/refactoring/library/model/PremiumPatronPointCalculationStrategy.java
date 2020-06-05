package com.bslota.refactoring.library.model;

/**
 * @author bslota on 12/01/2020
 */
class PremiumPatronPointCalculationStrategy implements PointCalculationStrategy {

    static final int TYPE_ID = 2;
    private static final int POINTS_MULTIPLYING_THRESHOLD = 0;
    private static final int DEFAULT_NUMBER_OF_POINTS = 100;
    private static final int MULTIPLIER = 2;

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() == POINTS_MULTIPLYING_THRESHOLD ?
                DEFAULT_NUMBER_OF_POINTS : loyalties.getPoints() * MULTIPLIER;
    }
}
