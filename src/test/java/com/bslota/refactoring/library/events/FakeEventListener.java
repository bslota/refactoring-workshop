package com.bslota.refactoring.library.events;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bslota on 07/06/2020
 */
public class FakeEventListener implements DomainEventsListener {

    private List<DomainEvent> events = new LinkedList<>();

    @Override
    public void handle(DomainEvent event) {
        events.add(event);
    }

    public DomainEvent lastConsumedEvent() {
        return events.isEmpty() ? null : events.get(events.size() - 1);
    }
}
