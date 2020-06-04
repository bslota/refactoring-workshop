package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.dao.BookDAO;
import com.bslota.refactoring.library.dao.PatronDAO;
import com.bslota.refactoring.library.model.Book;
import com.bslota.refactoring.library.model.Patron;
import org.springframework.stereotype.Service;

import java.time.Instant;

import static java.time.temporal.ChronoUnit.DAYS;

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
            if (maxNumberOfHoldsIsNotReachedBy(patron)) {
                if (isAvailable(book)) {
                    placeOnHold(bookId, patronId, days, book, patron);
                    bookDAO.update(book);
                    patronDAO.update(patron);
                    flag = true;
                }
            }
        }
        if (flag) {
            addLoyaltyPoints(patron);
            patronDAO.update(patron);
        }
        if (flag && patron.isQualifiesForFreeBook()) {
            sendNotificationAboutFreeBookRewardFor(patron);
        }
        return flag;
    }

    private void sendNotificationAboutFreeBookRewardFor(Patron patron) {
        String title = "[REWARD] Patron for free book reward waiting";
        String body = "Dear Colleague, \n" +
                "One of our patrons with ID " + patron.getPatronId() + " gathered " + patron.getPoints() + ". \n" +
                "Please contact him and prepare a free book reward!";
        String employees = "customerservice@your-library.com";
        emailService.sendMail(new String[]{employees}, "contact@your-library.com", title, body);
    }

    private void placeOnHold(int bookId, int patronId, int days, Book book, Patron patron) {
        patron.getHolds().add(bookId);
        book.setReservationDate(Instant.now());
        book.setReservationEndDate(Instant.now().plus(days, DAYS));
        book.setPatronId(patronId);
    }

    private boolean isAvailable(Book book) {
        return book.getReservationDate() == null;
    }

    private boolean maxNumberOfHoldsIsNotReachedBy(Patron patron) {
        return !(patron.getHolds().size() >= 5);
    }

    private boolean thereIsA(Patron patron) {
        return patron != null;
    }

    private boolean thereIsA(Book book) {
        return book != null;
    }

    private void addLoyaltyPoints(Patron patron) {
        int type = patron.getType();
        switch (type) {
            case 0: // regular patron
                patron.setPoints(patron.getPoints() + 1);
                break;
            case 1: // researcher
                patron.setPoints(patron.getPoints() + 5);
                break;
            case 2: //premium
                int newPoints;
                if (patron.getPoints() == 0) {
                    newPoints = 100;
                } else {
                    newPoints = patron.getPoints() * 2;
                }
                patron.setPoints(newPoints);
                break;
            default:
                break;
        }
        if (patron.getPoints() > 10000) {
            patron.setQualifiesForFreeBook(true);
        }
    }

}
