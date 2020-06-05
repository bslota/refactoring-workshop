package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.PatronDAO;
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

    boolean placeOnHold(int bookId, int patronId, int days) {
        Optional<Book> book = bookRepository.findBy(BookId.of(bookId));
        Optional<Patron> patron = patronRepository.findBy(PatronId.of(patronId));
        boolean flag = false;
        if (book.isPresent() && patron.isPresent()) {
            PlaceOnHoldResult result = patron.get().placeOnHold(book.get());
            if (result instanceof BookPlacedOnHold) {
                book.get().placedOnHold(patron.get().getPatronId(), days);
                bookRepository.update(book.get());
                patronRepository.update(patron.get());
                flag = true;
            }
        }
        if (flag) {
            patron.get().getPatronLoyalties().addLoyaltyPoints();
            patronRepository.update(patron.get());
        }
        if (flag && patron.get().getPatronLoyalties().isQualifiesForFreeBook()) {
            sendNotificationAboutFreeBookRewardFor(patron.get().getPatronLoyalties());
        }
        return flag;
    }

    private void sendNotificationAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailDetails details = mailDetailsFactory.getFreeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }

}