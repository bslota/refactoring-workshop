package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.Patron;

public class PatronLoyaltiesCalculation {

    static void addLoyaltyPointsTo(Patron patron) {
        int type = patron.getPatronLoyalties().getType();
        switch (type) {
            case 0: // regular patron
                patron.getPatronLoyalties().setPoints(patron.getPatronLoyalties().getPoints() + 1);
                break;
            case 1: // researcher
                patron.getPatronLoyalties().setPoints(patron.getPatronLoyalties().getPoints() + 5);
                break;
            case 2: //premium
                int newPoints;
                if (patron.getPatronLoyalties().getPoints() == 0) {
                    newPoints = 100;
                } else {
                    newPoints = patron.getPatronLoyalties().getPoints() * 2;
                }
                patron.getPatronLoyalties().setPoints(newPoints);
                break;
            default:
                break;
        }
        if (patron.getPatronLoyalties().getPoints() > 10000) {
            patron.getPatronLoyalties().setQualifiesForFreeBook(true);
        }
    }
}