package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.BookDAO;
import com.bslota.refactoring.library.dao.PatronDAO;
import com.bslota.refactoring.library.fixture.BookFixture;
import com.bslota.refactoring.library.fixture.PatronFixture;
import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.Patron;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 04/06/2020
 */
class BookServiceTest {

    private static final int ID_OF_NOT_EXISTING_BOOK = 1;
    private static final int ID_OF_NOT_EXISTING_PATRON = 2;
    private static final int PERIOD_IN_DAYS = 100;

    private BookDAO bookDAO = mock(BookDAO.class);
    private PatronDAO patronDAO = mock(PatronDAO.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);
    private BookService bookService = new BookService(bookDAO, patronDAO, notificationSender);

    @Test
    void shouldFailToPlaceNotExistingBookOnHold() {
        //when
        boolean result = bookService.placeOnHold(ID_OF_NOT_EXISTING_BOOK, ID_OF_NOT_EXISTING_PATRON, PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenPatronHasMaxNumberOfHolds() {
        //given
        Book book = availableBook();
        Patron patron = patronWithMaxNumberOfHolds();

        //when
        boolean result = bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenItIsNotAvailable() {
        //given
        Patron patron = patronWithMaxNumberOfHolds();
        Book book = unavailableBook();

        //when:
        boolean result = bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldSucceedToPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        boolean result = bookService.placeOnHold(book.getBookId(), patron.getPatronId(), PERIOD_IN_DAYS);

        //then
        assertTrue(result);
    }

    private Book availableBook() {
        Book book = BookFixture.availableBook();
        when(bookDAO.getBookFromDatabase(book.getBookId())).thenReturn(book);
        return book;
    }

    private Book unavailableBook() {
        Book book = BookFixture.unavailableBook();
        when(bookDAO.getBookFromDatabase(book.getBookId())).thenReturn(book);
        return book;
    }

    private Patron patronWithMaxNumberOfHolds() {
        Patron patron = PatronFixture.patronWithMaxNumberOfHolds();
        when(patronDAO.getPatronFromDatabase(patron.getPatronId())).thenReturn(patron);
        return patron;
    }

    private Patron patronWithoutHolds() {
        Patron patron = PatronFixture.patronWithoutHolds();
        when(patronDAO.getPatronFromDatabase(patron.getPatronId())).thenReturn(patron);
        return patron;
    }

}