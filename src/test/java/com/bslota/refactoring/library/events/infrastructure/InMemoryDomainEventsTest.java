package com.bslota.refactoring.library.events.infrastructure;

import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.FakeEvent;
import com.bslota.refactoring.library.events.FakeEventListener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bslota on 07/06/2020
 */
class InMemoryDomainEventsTest {

    private InMemoryDomainEvents inMemoryDomainEvents = new InMemoryDomainEvents();
    private FakeEventListener testEventListener = new FakeEventListener();

    @BeforeEach
    void registerListener() {
        inMemoryDomainEvents.register(testEventListener);
    }

    @Test
    void shouldHandlePublishedEvent() {
        //given
        DomainEvent event = new FakeEvent();

        //when
        inMemoryDomainEvents.publish(event);

        //then
        assertEquals(testEventListener.lastConsumedEvent(), event);
    }

}