package com.bslota.refactoring.library.fixture;

import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.BookId;

import java.time.Instant;

import static com.bslota.refactoring.library.fixture.BookFixture.BookBuilder.newBook;
import static java.time.temporal.ChronoUnit.DAYS;

/**
 * @author bslota on 15/11/2019
 */
public class BookFixture {

    private static final int EXISTING_BOOK_ID = 2;
    private static final int SOME_RESERVATION_PERIOD = 50;
    private static final String DEFAULT_BOOK_TYPE = "CIRCULATING";

    public static Book availableBook() {
        return newBook().build();
    }

    public static Book unavailableBook() {
        Instant reservationDate = Instant.now();
        return newBook()
                .withReservationDate(reservationDate)
                .withReservationEndDate(reservationDate.plus(SOME_RESERVATION_PERIOD, DAYS))
                .build();
    }

    public static class BookBuilder {
        private BookId bookId = BookId.of(EXISTING_BOOK_ID);
        private String type = DEFAULT_BOOK_TYPE;
        private Instant reservationDate;
        private Instant reservationEndDate;
        private int patronId;

        public static BookBuilder newBook() {
            return new BookBuilder();
        }

        public BookBuilder withId(BookId bookId) {
            this.bookId = bookId;
            return this;
        }

        BookBuilder thatIsCirculating() {
            this.type = "CIRCULATING";
            return this;
        }

        BookBuilder thatIsRestricted() {
            this.type = "RESTRICTED";
            return this;
        }

        BookBuilder withReservationDate(Instant reservationDate) {
            this.reservationDate = reservationDate;
            return this;
        }

        BookBuilder withReservationEndDate(Instant reservationEndDate) {
            this.reservationEndDate = reservationEndDate;
            return this;
        }

        BookBuilder withPatronId(int patronId) {
            this.patronId = patronId;
            return this;
        }

        public Book build() {
            return new Book(bookId, reservationDate, reservationEndDate, type, patronId);
        }
    }
}

