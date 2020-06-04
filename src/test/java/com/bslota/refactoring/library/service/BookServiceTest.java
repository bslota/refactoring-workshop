package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.BookDAO;
import com.bslota.refactoring.library.dao.PatronDAO;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * @author bslota on 04/06/2020
 */
class BookServiceTest {

    private BookDAO bookDAO = mock(BookDAO.class);
    private PatronDAO patronDAO = mock(PatronDAO.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);
    private BookService bookService = new BookService(bookDAO, patronDAO, notificationSender);

    @Test
    void shouldFailToPlaceNotExistingBookOnHold() {

    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenPatronHasMaxNumberOfHolds() {

    }

    @Test
    void shouldFailToPlaceBookOnHoldWhenItIsNotAvailable() {

    }

    @Test
    void shouldSucceedToPlaceBookOnHold() {

    }
}