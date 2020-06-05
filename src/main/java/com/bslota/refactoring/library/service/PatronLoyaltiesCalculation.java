package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.PatronLoyalties;

public class PatronLoyaltiesCalculation {

    static void addLoyaltyPointsTo(PatronLoyalties patronLoyalties) {
        int type = patronLoyalties.getType();
        switch (type) {
            case 0: // regular patron
                patronLoyalties.setPoints(patronLoyalties.getPoints() + 1);
                break;
            case 1: // researcher
                patronLoyalties.setPoints(patronLoyalties.getPoints() + 5);
                break;
            case 2: //premium
                int newPoints;
                if (patronLoyalties.getPoints() == 0) {
                    newPoints = 100;
                } else {
                    newPoints = patronLoyalties.getPoints() * 2;
                }
                patronLoyalties.setPoints(newPoints);
                break;
            default:
                break;
        }
        if (patronLoyalties.getPoints() > 10000) {
            patronLoyalties.setQualifiesForFreeBook(true);
        }
    }
}