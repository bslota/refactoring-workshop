package com.bslota.refactoring.library.holding.infrastructure.db;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.Instant;

@Entity
class BookEntity {

    @Id
    private int bookId;
    private Instant reservationDate;
    private Instant reservationEndDate;
    private String type;
    private String title;
    private String author;
    @OneToOne
    private PatronEntity patronEntity;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Instant getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(Instant reservationDate) {
        this.reservationDate = reservationDate;
    }

    public Instant getReservationEndDate() {
        return reservationEndDate;
    }

    public void setReservationEndDate(Instant reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public PatronEntity getPatronEntity() {
        return patronEntity;
    }

    public void setPatronEntity(PatronEntity patronEntity) {
        this.patronEntity = patronEntity;
    }
}
