package com.bslota.refactoring.library.util;

import com.bslota.refactoring.library.model.PatronLoyalties;

import java.util.Collections;
import java.util.List;

/**
 * @author bslota on 12/01/2020
 */
public class MailUtils {

    public static MailDetails freeBookRewardNotificationFor(PatronLoyalties patronLoyalties) {
        String title = "[REWARD] Patron for free book reward waiting";
        String body = "Dear Colleague, \n" +
                "One of our patrons with ID " + patronLoyalties.getPatronId() + " gathered " + patronLoyalties.getPoints() + ". \n" +
                "Please contact him and prepare a free book reward!";
        String employees = "customerservice@your-library.com";
        return MailDetails.from(title, body, Collections.singletonList(employees));
    }

    public static class MailDetails {
        private final String title;
        private final String body;
        private final List<String> recipients;

        private MailDetails(String title, String body, List<String> recipients) {
            this.title = title;
            this.body = body;
            this.recipients = recipients;
        }

        public static MailDetails from(String title, String body, List<String> recipient) {
            return new MailDetails(title, body, recipient);
        }

        public String title() {
            return title;
        }

        public String body() {
            return body;
        }

        public String[] recipients() {
            return recipients.toArray(new String[]{});
        }
    }
}
