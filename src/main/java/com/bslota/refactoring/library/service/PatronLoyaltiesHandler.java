package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.events.BookPlacedOnHold;
import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.DomainEventsListener;
import com.bslota.refactoring.library.model.PatronId;
import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.model.PatronLoyaltiesRepository;
import com.bslota.refactoring.library.util.MailDetails;
import com.bslota.refactoring.library.util.MailDetailsFactory;
import org.springframework.stereotype.Component;

/**
 * @author bslota on 07/06/2020
 */
@Component
class PatronLoyaltiesHandler implements DomainEventsListener {

    private final PatronLoyaltiesRepository patronLoyaltiesRepository;
    private final NotificationSender emailService;
    private final MailDetailsFactory mailDetailsFactory;

    PatronLoyaltiesHandler(PatronLoyaltiesRepository patronLoyaltiesRepository, NotificationSender emailService, MailDetailsFactory mailDetailsFactory) {
        this.patronLoyaltiesRepository = patronLoyaltiesRepository;
        this.emailService = emailService;
        this.mailDetailsFactory = mailDetailsFactory;
    }

    @Override
    public void handle(DomainEvent event) {
        if (event instanceof BookPlacedOnHold) {
            handle((BookPlacedOnHold) event);
        }
    }

    private void handle(BookPlacedOnHold event) {
        PatronLoyalties patronLoyalties = getPatronLoyalties(PatronId.of(event.getPatronId()));
        patronLoyalties.addLoyaltyPoints();
        patronLoyaltiesRepository.update(patronLoyalties);
        if (patronLoyalties.isQualifiesForFreeBook()) {
            sendNotificationAboutFreeBookRewardFor(patronLoyalties);
        }
    }

    private PatronLoyalties getPatronLoyalties(PatronId patronId) {
        return patronLoyaltiesRepository.findBy(patronId)
                .orElse(PatronLoyalties.emptyFor(patronId));
    }

    private void sendNotificationAboutFreeBookRewardFor(PatronLoyalties patronLoyalties) {
        MailDetails details = mailDetailsFactory.getFreeBookRewardNotificationFor(patronLoyalties);
        emailService.sendMail(details.recipients(), "contact@your-library.com", details.title(), details.body());
    }


}