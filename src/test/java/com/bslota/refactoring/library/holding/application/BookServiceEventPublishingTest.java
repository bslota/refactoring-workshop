package com.bslota.refactoring.library.holding.application;

import com.bslota.refactoring.library.events.BookPlacedOnHold;
import com.bslota.refactoring.library.events.FakeEventListener;
import com.bslota.refactoring.library.events.infrastructure.InMemoryDomainEvents;
import com.bslota.refactoring.library.holding.fixture.BookFixture;
import com.bslota.refactoring.library.holding.fixture.PatronFixture;
import com.bslota.refactoring.library.holding.model.Book;
import com.bslota.refactoring.library.holding.model.BookRepository;
import com.bslota.refactoring.library.holding.model.Patron;
import com.bslota.refactoring.library.holding.model.PatronRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 04/06/2020
 */
class BookServiceEventPublishingTest {

    private static final int ID_OF_NOT_EXISTING_BOOK = 1;
    private static final int ID_OF_NOT_EXISTING_PATRON = 2;
    private static final int PERIOD_IN_DAYS = 100;

    private BookRepository bookRepository = mock(BookRepository.class);
    private PatronRepository patronRepository = mock(PatronRepository.class);
    private InMemoryDomainEvents domainEvents = new InMemoryDomainEvents();
    private FakeEventListener eventListener = new FakeEventListener();
    private PlacingOnHold bookService = new PlacingOnHold(bookRepository, patronRepository, domainEvents);

    @BeforeEach
    void registerEventListener() {
        eventListener.clear();
        domainEvents.register(eventListener);
    }

    @Test
    void shouldNotPublishEventWhenFailedToPlaceNotExistingBookOnHold() {
        //when
        bookService.placeOnHold(ID_OF_NOT_EXISTING_BOOK, ID_OF_NOT_EXISTING_PATRON, PERIOD_IN_DAYS);

        //then
        assertNull(eventListener.lastConsumedEvent());
    }

    @Test
    void shouldPublishEventWhenSucceedToPlaceBookOnHold() {
        //given
        Book book = availableBook();
        Patron patron = patronWithoutHolds();

        //when
        bookService.placeOnHold(book.getBookId().asInt(), patron.getPatronId().asInt(), PERIOD_IN_DAYS);

        //then
        bookPlacedOnHoldEventWasPublishedFor(book, patron);
    }

    private void bookPlacedOnHoldEventWasPublishedFor(Book book, Patron patron) {
        assertTrue(eventListener.lastConsumedEvent() instanceof BookPlacedOnHold);
        assertEquals(patron.getPatronId().asInt(), ((BookPlacedOnHold) eventListener.lastConsumedEvent()).getPatronId());
        assertEquals(book.getBookId().asInt(), ((BookPlacedOnHold) eventListener.lastConsumedEvent()).getBookId());
    }

    private Book availableBook() {
        Book book = BookFixture.availableBook();
        when(bookRepository.findBy(book.getBookId())).thenReturn(Optional.of(book));
        return book;
    }

    private Patron patronWithoutHolds() {
        Patron patron = PatronFixture.patronWithoutHolds();
        when(patronRepository.findBy(patron.getPatronId())).thenReturn(Optional.of(patron));
        return patron;
    }

}