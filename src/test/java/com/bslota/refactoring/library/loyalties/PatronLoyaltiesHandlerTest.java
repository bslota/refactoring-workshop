package com.bslota.refactoring.library.loyalties;

import com.bslota.refactoring.library.events.BookPlacedOnHold;
import com.bslota.refactoring.library.events.DomainEvents;
import com.bslota.refactoring.library.events.infrastructure.InMemoryDomainEvents;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static com.bslota.refactoring.library.holding.fixture.BookFixture.availableBook;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author bslota on 07/06/2020
 */
class PatronLoyaltiesHandlerTest {

    private DomainEvents domainEvents = new InMemoryDomainEvents();
    private PatronLoyaltiesRepository patronLoyaltiesRepository = mock(PatronLoyaltiesRepository.class);
    private NotificationSender notificationSender = mock(NotificationSender.class);
    private PatronLoyaltiesHandler handler = new PatronLoyaltiesHandler(patronLoyaltiesRepository, notificationSender, new MailDetailsFactory());

    @BeforeEach
    void registerHandler() {
        domainEvents.register(handler);
    }

    @Test
    void shouldUpdatePatronLoyaltiesWhenBookWasPlacedOnHold() {
        //given
        PatronLoyalties loyalties = patronLoyaltiesWithoutPoints();
        BookPlacedOnHold event = BookPlacedOnHold.of(loyalties.getPatronId().asInt(), availableBook().getBookId().asInt());

        //when
        domainEvents.publish(event);

        //then
        verify(patronLoyaltiesRepository, atLeastOnce()).update(any()); //we should be more specific

    }

    @Test
    void shouldSendNotificationWhenBookWasPlacedOnHold() {
        //given
        PatronLoyalties loyalties = patronLoyaltiesQualifyingForFreeBook();
        BookPlacedOnHold event = BookPlacedOnHold.of(loyalties.getPatronId().asInt(), availableBook().getBookId().asInt());

        //when
        domainEvents.publish(event);

        //then
        verify(notificationSender, atLeastOnce()).sendMail(any(), any(), any(), any());
    }

    PatronLoyalties patronLoyaltiesWithoutPoints() {
        PatronLoyalties patronLoyalties = PatronLoyaltiesFixture.PatronLoyaltiesBuilder.patronLoyalties().empty().build();
        when(patronLoyaltiesRepository.findBy(patronLoyalties.getPatronId())).thenReturn(Optional.of(patronLoyalties));
        return patronLoyalties;
    }

    PatronLoyalties patronLoyaltiesQualifyingForFreeBook() {
        PatronLoyalties patronLoyalties = PatronLoyaltiesFixture.PatronLoyaltiesBuilder.patronLoyalties().withValueQualifyingForFreeBook().build();
        when(patronLoyaltiesRepository.findBy(patronLoyalties.getPatronId())).thenReturn(Optional.of(patronLoyalties));
        return patronLoyalties;
    }
}
