package com.bslota.refactoring.library.util;

import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.util.MailDetails;
import com.bslota.refactoring.library.util.MailUtils;
import org.springframework.stereotype.Component;

@Component
public class MailDetailsFactory {

    public MailDetails getFreeBookRewardNotificationFor(PatronLoyalties patronLoyalties) {
        return MailUtils.freeBookRewardNotificationFor(patronLoyalties);
    }
}