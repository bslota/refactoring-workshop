package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.PatronDAO;
import com.bslota.refactoring.library.fixture.BookFixture;
import com.bslota.refactoring.library.fixture.PatronFixture;
import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.BookRepository;
import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.model.PatronRepository;
import com.bslota.refactoring.library.util.MailDetailsFactory;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 04/06/2020
 */
class BookServiceTest {

    private static final int ID_OF_NOT_EXISTING_BOOK = 1;
    private static final int ID_OF_NOT_EXISTING_PATRON = 2;
    private static final int PERIOD_IN_DAYS = 100;

    private BookRepository bookRepository = mock(BookRepository.class);
    private PatronRepository patronRepository = mock(PatronRepository.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);
    private BookService bookService = new BookService(bookRepository, patronRepository, notificationSender, new MailDetailsFactory());

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
        boolean result = bookService.placeOnHold(book.getBookId().asInt(), patron.getPatronId().asInt(), PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenItIsNotAvailable() {
        //given
        Patron patron = patronWithMaxNumberOfHolds();
        Book book = unavailableBook();

        //when:
        boolean result = bookService.placeOnHold(book.getBookId().asInt(), patron.getPatronId().asInt(), PERIOD_IN_DAYS);

        //then
        assertFalse(result);
    }

    @Test
    void shouldSucceedToPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        boolean result = bookService.placeOnHold(book.getBookId().asInt(), patron.getPatronId().asInt(), PERIOD_IN_DAYS);

        //then
        assertTrue(result);
    }

    @Test
    void shouldNotSaveBookPatronOrSendNotificationWhenFailedToPlaceNotExistingBookOnHold() {
        //when
        bookService.placeOnHold(ID_OF_NOT_EXISTING_BOOK, ID_OF_NOT_EXISTING_PATRON, PERIOD_IN_DAYS);

        //then
        verify(bookRepository, never()).update(any());
        verify(patronRepository, never()).update(any());
        verify(notificationSender, never()).sendMail(any(), any(), any(), any());
    }

    @Test
    void shouldSaveBookPatronSucceedToPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        bookService.placeOnHold(book.getBookId().asInt(), patron.getPatronId().asInt(), PERIOD_IN_DAYS);

        //then
        verify(bookRepository, atLeastOnce()).update(any());
        verify(patronRepository, atLeastOnce()).update(any());
        verify(notificationSender, never()).sendMail(any(), any(), any(), any());
    }

    @Test
    void shouldSendNotificationWhenPatronQualifiesForFreeBook() {
        //given
        Book book = availableBook();
        Patron patron = patronQualifyingForFreeBook();

        //when
        bookService.placeOnHold(book.getBookId().asInt(), patron.getPatronId().asInt(), PERIOD_IN_DAYS);

        //then
        verify(notificationSender, atLeastOnce()).sendMail(any(), any(), any(), any());
    }

    private Book availableBook() {
        Book book = BookFixture.availableBook();
        when(bookRepository.findBy(book.getBookId())).thenReturn(Optional.of(book));
        return book;
    }

    private Book unavailableBook() {
        Book book = BookFixture.unavailableBook();
        when(bookRepository.findBy(book.getBookId())).thenReturn(Optional.of(book));
        return book;
    }

    private Patron patronWithMaxNumberOfHolds() {
        Patron patron = PatronFixture.patronWithMaxNumberOfHolds();
        when(patronRepository.findBy(patron.getPatronId())).thenReturn(Optional.of(patron));
        return patron;
    }

    private Patron patronWithoutHolds() {
        Patron patron = PatronFixture.patronWithoutHolds();
        when(patronRepository.findBy(patron.getPatronId())).thenReturn(Optional.of(patron));
        return patron;
    }

    private Patron patronQualifyingForFreeBook() {
        Patron patron = PatronFixture.patronQualifyingForFreeBook();
        when(patronRepository.findBy(patron.getPatronId())).thenReturn(Optional.of(patron));
        return patron;
    }

}