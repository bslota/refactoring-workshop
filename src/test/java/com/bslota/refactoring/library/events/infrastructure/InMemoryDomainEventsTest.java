package com.bslota.refactoring.library.events.infrastructure;

import com.bslota.refactoring.library.events.BaseDomainEvent;
import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.DomainEvents;
import com.bslota.refactoring.library.events.DomainEventsListener;
import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author bslota on 07/06/2020
 */
class InMemoryDomainEventsTest {

    private InMemoryDomainEvents inMemoryDomainEvents = new InMemoryDomainEvents();
    private TestEventListener testEventListener = new TestEventListener();

    @BeforeEach
    void registerListener() {
        inMemoryDomainEvents.register(testEventListener);
    }

    @Test
    void shouldHandlePublishedEvent() {
        //given
        DomainEvent event = new TestEvent();

        //when
        inMemoryDomainEvents.publish(event);

        //then
        assertEquals(testEventListener.lastConsumedEvent(), event);
    }

    static class TestEvent extends BaseDomainEvent {

        public TestEvent() {
            super(randomAlphabetic(10));
        }
    }

    static class TestEventListener implements DomainEventsListener {

        private List<DomainEvent> events = new LinkedList<>();

        @Override
        public void handle(DomainEvent event) {
            events.add(event);
        }

        DomainEvent lastConsumedEvent() {
            return events.get(events.size() - 1);
        }
    }
}