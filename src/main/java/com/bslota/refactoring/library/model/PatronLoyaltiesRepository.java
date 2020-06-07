package com.bslota.refactoring.library.model;

import java.util.Optional;

/**
 * @author bslota on 06/06/2020
 */
public interface PatronLoyaltiesRepository {

    Optional<PatronLoyalties> findBy(PatronId patronId);

    void update(PatronLoyalties patron);
}
