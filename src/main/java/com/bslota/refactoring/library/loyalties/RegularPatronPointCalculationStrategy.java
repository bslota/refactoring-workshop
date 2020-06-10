package com.bslota.refactoring.library.loyalties;

/**
 * @author bslota on 12/01/2020
 */
class RegularPatronPointCalculationStrategy implements PointCalculationStrategy {

    static final int TYPE_ID = 0;
    private static final int NUMBER_OF_POINTS_TO_ADD = 1;

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() + NUMBER_OF_POINTS_TO_ADD;
    }
}
