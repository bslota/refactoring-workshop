package com.bslota.refactoring.library.holding.application;

import com.bslota.refactoring.library.events.BookPlacedOnHold;
import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.DomainEvents;
import com.bslota.refactoring.library.holding.model.Book;
import com.bslota.refactoring.library.holding.model.BookId;
import com.bslota.refactoring.library.holding.model.BookRepository;
import com.bslota.refactoring.library.holding.model.Patron;
import com.bslota.refactoring.library.holding.model.PatronId;
import com.bslota.refactoring.library.holding.model.PatronRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlacingOnHold {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final DomainEvents domainEvents;

    PlacingOnHold(BookRepository bookRepository, PatronRepository patronRepository, DomainEvents domainEvents) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.domainEvents = domainEvents;
    }

    @Transactional
    public boolean placeOnHold(int bookId, int patronId, int days) {
        Optional<Book> book = bookRepository.findBy(BookId.of(bookId));
        Optional<Patron> patron = patronRepository.findBy(PatronId.of(patronId));
        if (book.isPresent() && patron.isPresent()) {
            Patron foundPatron = patron.get();
            Book foundBook = book.get();
            return placeOnHold(foundPatron, foundBook, days);
        }
        return false;
    }

    private boolean placeOnHold(Patron patron, Book book, int days) {
        DomainEvent result = patron.placeOnHold(book);
        if (result instanceof BookPlacedOnHold) {
            book.placedOnHold(patron.getPatronId(), days);
            bookRepository.update(book);
            patronRepository.update(patron);
            domainEvents.publish(result);
            return true;
        }
        return false;
    }
}