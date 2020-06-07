package com.bslota.refactoring.library.events;

import java.time.Instant;
import java.util.UUID;

/**
 * @author bslota on 07/06/2020
 */
public interface DomainEvent {

    UUID id();

    String aggregateId();

    Instant occurredAt();
}
