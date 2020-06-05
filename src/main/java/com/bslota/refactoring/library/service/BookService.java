package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.PatronDAO;
import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.BookId;
import com.bslota.refactoring.library.model.BookPlacedOnHold;
import com.bslota.refactoring.library.model.BookRepository;
import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.model.PlaceOnHoldResult;
import com.bslota.refactoring.library.util.MailDetails;
import com.bslota.refactoring.library.util.MailDetailsFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final PatronDAO patronDAO;
    private final NotificationSender emailService;
    private final MailDetailsFactory mailDetailsFactory;

    BookService(BookRepository bookRepository, PatronDAO patronDAO, NotificationSender emailService, MailDetailsFactory mailDetailsFactory) {
        this.bookRepository = bookRepository;
        this.patronDAO = patronDAO;
        this.emailService = emailService;
        this.mailDetailsFactory = mailDetailsFactory;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Optional<Book> book = bookRepository.findBy(BookId.of(bookId));
        Patron patron = patronDAO.getPatronFromDatabase(patronId);
        boolean flag = false;
        if (book.isPresent() && thereIsA(patron)) {
            PlaceOnHoldResult result = patron.placeOnHold(book.get());
            if (result instanceof BookPlacedOnHold) {
                book.get().placedOnHold(patron.getPatronId(), days);
                bookRepository.update(book.get());
                patronDAO.update(patron);
                flag = true;
            }
        }
        if (flag) {
            patron.getPatronLoyalties().addLoyaltyPoints();
            patronDAO.update(patron);
        }
        if (flag && patron.getPatronLoyalties().isQualifiesForFreeBook()) {
            sendNotificationAboutFreeBookRewardFor(patron.getPatronLoyalties());
        }
        return flag;
    }

    private void sendNotificationAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailDetails details = mailDetailsFactory.getFreeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }

    private boolean thereIsA(Patron patron) {
        return patron != null;
    }

    private boolean thereIsA(Book book) {
        return book != null;
    }

}
