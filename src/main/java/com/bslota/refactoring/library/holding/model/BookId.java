package com.bslota.refactoring.library.holding.model;

import java.util.Objects;

/**
 * @author bslota on 23/11/2019
 */
public class BookId {

    private final int value;

    public BookId(int value) {
        this.value = value;
    }

    public static BookId of(int value) {
        return new BookId(value);
    }

    public int asInt() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookId patronId = (BookId) o;
        return value == patronId.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    int getValue() {
        return value;
    }
}
