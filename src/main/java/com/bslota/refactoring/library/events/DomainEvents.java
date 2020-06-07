package com.bslota.refactoring.library.events;

/**
 * @author bslota on 07/06/2020
 */
public interface DomainEvents {

    void publish(DomainEvent event);

    default void register(DomainEventsListener listener) {
    }
}
