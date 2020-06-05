package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.PatronLoyalties;

public class PatronLoyaltiesCalculation {

    static void addLoyaltyPointsTo(PatronLoyalties patronLoyalties) {
        patronLoyalties.addLoyaltyPoints();
    }
}