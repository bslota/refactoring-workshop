package com.bslota.refactoring.library.events.infrastructure;

import com.bslota.refactoring.library.events.DomainEvent;
import com.bslota.refactoring.library.events.DomainEvents;
import com.bslota.refactoring.library.events.DomainEventsListener;

import java.util.LinkedList;
import java.util.List;

/**
 * @author bslota on 07/06/2020
 */
public class InMemoryDomainEvents implements DomainEvents {

    private List<DomainEventsListener> listeners = new LinkedList<>();

    @Override
    public void publish(DomainEvent event) {
        listeners.forEach(it -> it.handle(event));
    }

    @Override
    public void register(DomainEventsListener listener) {
        this.listeners.add(listener);
    }
}
