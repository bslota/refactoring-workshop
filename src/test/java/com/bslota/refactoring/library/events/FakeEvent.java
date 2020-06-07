package com.bslota.refactoring.library.events;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;

/**
 * @author bslota on 07/06/2020
 */
public class FakeEvent extends BaseDomainEvent {

    public FakeEvent() {
        super(randomAlphabetic(10));
    }
}
