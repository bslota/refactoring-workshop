package com.bslota.refactoring.library.util;

import com.bslota.refactoring.library.model.PatronLoyalties;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class MailDetailsFactory {

    public MailDetails getFreeBookRewardNotificationFor(PatronLoyalties patronLoyalties) {
        String title = "[REWARD] Patron for free book reward waiting";
        String body = "Dear Colleague, \n" +
                "One of our patrons with ID " + patronLoyalties.getPatronId() + " gathered " + patronLoyalties.getPoints() + ". \n" +
                "Please contact him and prepare a free book reward!";
        String employees = "customerservice@your-library.com";
        return MailDetails.from(title, body, Collections.singletonList(employees));
    }
}