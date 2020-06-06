package com.bslota.refactoring.library.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
public class PatronLoyaltiesEntity {

    @Id
    private int patronId;
    private int patronType;
    private int points;
    private boolean qualifiesForFreeBook;

    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public int getPatronType() {
        return patronType;
    }

    public void setPatronType(int patronType) {
        this.patronType = patronType;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isQualifiesForFreeBook() {
        return qualifiesForFreeBook;
    }

    public void setQualifiesForFreeBook(boolean qualifiesForFreeBook) {
        this.qualifiesForFreeBook = qualifiesForFreeBook;
    }
}
