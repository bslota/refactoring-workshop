package com.bslota.refactoring.library.loyalties;

import com.bslota.refactoring.library.events.BookPlacedOnHold;
import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.DomainEventsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
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
