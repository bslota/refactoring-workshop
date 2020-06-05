package com.bslota.refactoring.library.model;

/**
 * @author bslota on 05/06/2020
 */
class PatronId {

    private final int value;

    private PatronId(int value) {
        this.value = value;
    }

    static PatronId of(int i) {
        return new PatronId(i);
    }

    int asInt() {
        return value;
    }
}
