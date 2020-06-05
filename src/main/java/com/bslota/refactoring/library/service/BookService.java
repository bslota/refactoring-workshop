package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.BookId;
import com.bslota.refactoring.library.model.BookPlacedOnHold;
import com.bslota.refactoring.library.model.BookRepository;
import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.model.PatronId;
import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.model.PatronRepository;
import com.bslota.refactoring.library.model.PlaceOnHoldResult;
import com.bslota.refactoring.library.util.MailDetails;
import com.bslota.refactoring.library.util.MailDetailsFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PatronRepository patronRepository;
    private final NotificationSender emailService;
    private final MailDetailsFactory mailDetailsFactory;

    BookService(BookRepository bookRepository, PatronRepository patronRepository, NotificationSender emailService, MailDetailsFactory mailDetailsFactory) {
        this.bookRepository = bookRepository;
        this.patronRepository = patronRepository;
        this.emailService = emailService;
        this.mailDetailsFactory = mailDetailsFactory;
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
        PlaceOnHoldResult result = patron.placeOnHold(book);
        if (result instanceof BookPlacedOnHold) {
            book.placedOnHold(patron.getPatronId(), days);
            patron.getPatronLoyalties().addLoyaltyPoints();
            bookRepository.update(book);
            patronRepository.update(patron);
            if (patron.getPatronLoyalties().isQualifiesForFreeBook()) {
                sendNotificationAboutFreeBookRewardFor(patron.getPatronLoyalties());
            }
            return true;
        }
        return false;
    }

    private void sendNotificationAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailDetails details = mailDetailsFactory.getFreeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }

}