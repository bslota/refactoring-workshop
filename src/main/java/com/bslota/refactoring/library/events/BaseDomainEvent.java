package com.bslota.refactoring.library.events;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

/**
 * @author bslota on 07/06/2020
 */
public abstract class BaseDomainEvent implements DomainEvent {

    private final UUID id;
    private final String aggregateId;
    private final Instant occurredAt;

    public BaseDomainEvent(String aggregateId) {
        this.id = UUID.randomUUID();
        this.occurredAt = Instant.now();
        this.aggregateId = aggregateId;
    }

    @Override
    public UUID id() {
        return null;
    }

    @Override
    public String aggregateId() {
        return null;
    }

    @Override
    public Instant occurredAt() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseDomainEvent that = (BaseDomainEvent) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
