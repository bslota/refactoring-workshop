package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.BookDAO;
import com.bslota.refactoring.library.dao.PatronDAO;
import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.BookPlacedOnHold;
import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.model.PlaceOnHoldResult;
import com.bslota.refactoring.library.util.MailUtils;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookDAO bookDAO;
    private final PatronDAO patronDAO;
    private final NotificationSender emailService;

    BookService(BookDAO bookDAO, PatronDAO patronDAO, NotificationSender emailService) {
        this.bookDAO = bookDAO;
        this.patronDAO = patronDAO;
        this.emailService = emailService;
    }

    boolean placeOnHold(int bookId, int patronId, int days) {
        Book book = bookDAO.getBookFromDatabase(bookId);
        Patron patron = patronDAO.getPatronFromDatabase(patronId);
        boolean flag = false;
        if (thereIsA(book) && thereIsA(patron)) {
            PlaceOnHoldResult result = patron.placeOnHold(book);
            if (result instanceof BookPlacedOnHold) {
                book.placedOnHold(patron.getPatronId(), days);
                bookDAO.update(book);
                patronDAO.update(patron);
                flag = true;
            }
        }
        if (flag) {
            PatronLoyaltiesCalculation.addLoyaltyPointsTo(patron.getPatronLoyalties());
            patronDAO.update(patron);
        }
        if (flag && patron.getPatronLoyalties().isQualifiesForFreeBook()) {
            sendNotificationAboutFreeBookRewardFor(patron.getPatronLoyalties());
        }
        return flag;
    }

    private void sendNotificationAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailUtils.MailDetails details = MailUtils.freeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }

    private boolean thereIsA(Patron patron) {
        return patron != null;
    }

    private boolean thereIsA(Book book) {
        return book != null;
    }

}
