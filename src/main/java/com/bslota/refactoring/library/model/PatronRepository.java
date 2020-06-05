package com.bslota.refactoring.library.model;

import java.util.Optional;

/**
 * @author bslota on 06/06/2020
 */
public interface PatronRepository {

    Optional<Patron> findBy(PatronId patronId);

    void update(Patron patron);
}
