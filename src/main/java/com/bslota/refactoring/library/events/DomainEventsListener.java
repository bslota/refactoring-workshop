package com.bslota.refactoring.library.events;

/**
 * @author bslota on 07/06/2020
 */
public interface DomainEventsListener {

    void handle(DomainEvent event);
}
