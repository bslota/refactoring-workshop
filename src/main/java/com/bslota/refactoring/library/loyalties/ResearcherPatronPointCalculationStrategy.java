package com.bslota.refactoring.library.loyalties;

/**
 * @author bslota on 12/01/2020
 */
class ResearcherPatronPointCalculationStrategy implements PointCalculationStrategy {

    static final int TYPE_ID = 1;
    private static final int NUMBER_OF_POINTS_TO_ADD = 5;

    @Override
    public int calculate(PatronLoyalties loyalties) {
        return loyalties.getPoints() + NUMBER_OF_POINTS_TO_ADD;
    }
}
