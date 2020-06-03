package com.bslota.refactoring.library.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

import static javax.persistence.FetchType.EAGER;

@Entity
public class PatronEntity {

    @Id
    private int patronId;

    private int type;
    private int points;
    private boolean qualifiesForFreeBook;
    @OneToMany(fetch = EAGER)
    private Set<BookEntity> holds;


    public int getPatronId() {
        return patronId;
    }

    public void setPatronId(int patronId) {
        this.patronId = patronId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public Set<BookEntity> getHolds() {
        return holds;
    }

    public void setHolds(Set<BookEntity> holds) {
        this.holds = holds;
    }
}
