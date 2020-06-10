package com.bslota.refactoring.library.holding.model;

import java.util.Objects;

/**
 * @author bslota on 05/06/2020
 */
public class PatronId {

    private final int value;

    private PatronId(int value) {
        this.value = value;
    }

    public static PatronId of(int i) {
        return new PatronId(i);
    }

    public int asInt() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PatronId patronId = (PatronId) o;
        return value == patronId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
