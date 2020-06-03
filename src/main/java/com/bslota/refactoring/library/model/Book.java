package com.bslota.refactoring.library.model;

import java.time.Instant;

public class Book {
    private int bookId;
    private Instant reservationDate;
    private Instant reservationEndDate;
    private String type;
    private Integer patronId;

    public Book(int bookId, Instant reservationDate, Instant reservationEndDate, String type, Integer patronId) {
        this.bookId = bookId;
        this.reservationDate = reservationDate;
        this.reservationEndDate = reservationEndDate;
        this.type = type;
        this.patronId = patronId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setReservationDate(Instant now) {
        this.reservationDate = now;
    }

    public void setReservationEndDate(Instant date) {
        this.reservationEndDate = date;
    }

    public Instant getReservationDate() {
        return this.reservationDate;
    }

    public Instant getReservationEndDate() {
        return reservationEndDate;
    }

    public void setPatronId(Integer patronId) {
        this.patronId = patronId;
    }

    public Integer getPatronId() {
        return patronId;
    }

    String getType() {
        return this.type;
    }
}
