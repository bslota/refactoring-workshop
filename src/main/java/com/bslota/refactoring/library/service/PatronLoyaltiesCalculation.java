package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.Patron;

public class PatronLoyaltiesCalculation {

    static void addLoyaltyPoints(Patron patron) {
        int type = patron.getType();
        switch (type) {
            case 0: // regular patron
                patron.setPoints(patron.getPoints() + 1);
                break;
            case 1: // researcher
                patron.setPoints(patron.getPoints() + 5);
                break;
            case 2: //premium
                int newPoints;
                if (patron.getPoints() == 0) {
                    newPoints = 100;
                } else {
                    newPoints = patron.getPoints() * 2;
                }
                patron.setPoints(newPoints);
                break;
            default:
                break;
        }
        if (patron.getPoints() > 10000) {
            patron.setQualifiesForFreeBook(true);
        }
    }
}