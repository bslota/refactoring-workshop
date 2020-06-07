package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.events.FakeEventListener;
import com.bslota.refactoring.library.events.infrastructure.InMemoryDomainEvents;
import com.bslota.refactoring.library.model.BookRepository;
import com.bslota.refactoring.library.model.PatronLoyaltiesRepository;
import com.bslota.refactoring.library.model.PatronRepository;
import com.bslota.refactoring.library.util.MailDetailsFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

/**
 * @author bslota on 04/06/2020
 */
class BookServiceEventPublishingTest {

    private static final int ID_OF_NOT_EXISTING_BOOK = 1;
    private static final int ID_OF_NOT_EXISTING_PATRON = 2;
    private static final int PERIOD_IN_DAYS = 100;

    private BookRepository bookRepository = mock(BookRepository.class);
    private PatronRepository patronRepository = mock(PatronRepository.class);
    private PatronLoyaltiesRepository patronLoyaltiesRepository = mock(PatronLoyaltiesRepository.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);
    private InMemoryDomainEvents domainEvents = new InMemoryDomainEvents();
    private FakeEventListener eventListener = new FakeEventListener();
    private BookService bookService = new BookService(bookRepository, patronRepository, patronLoyaltiesRepository, notificationSender, domainEvents, new MailDetailsFactory());

    @BeforeEach
    void registerEventListener() {
        domainEvents.register(eventListener);
    }

    @Test
    void shouldNotPublishEventWhenFailedToPlaceNotExistingBookOnHold() {

    }

    @Test
    void shouldPublishEventWhenSucceedToPlaceBookOnHold() {
    }

}