package com.bslota.refactoring.library.util;

import java.util.List;

/**
 * @author bslota on 06/06/2020
 */
public class MailDetails {
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
