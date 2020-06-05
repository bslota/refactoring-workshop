package com.bslota.refactoring.library.model;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;

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

    public Instant getReservationDate() {
        return this.reservationDate;
    }

    public Instant getReservationEndDate() {
        return reservationEndDate;
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

    public void placedOnHold(PatronId patronId, int days) {
        this.reservationDate = Instant.now();
        this.reservationEndDate = Instant.now().plus(days, DAYS);
        this.patronId = patronId.asInt();
    }

    public boolean isAvailable() {
        return reservationDate == null;
    }
}
