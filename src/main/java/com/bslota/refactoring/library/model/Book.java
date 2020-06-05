package com.bslota.refactoring.library.model;

import java.time.Instant;

public class Book {
    private BookId bookId;
    private Instant reservationDate;
    private Instant reservationEndDate;
    private String type;
    private Integer patronId;

    public Book(BookId bookId, Instant reservationDate, Instant reservationEndDate, String type, Integer patronId) {
        this.setBookId(bookId);
        this.reservationDate = reservationDate;
        this.reservationEndDate = reservationEndDate;
        this.type = type;
        this.patronId = patronId;
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

    public BookId getBookId() {
        return bookId;
    }

    public void setBookId(BookId bookId) {
        this.bookId = bookId;
    }
}
