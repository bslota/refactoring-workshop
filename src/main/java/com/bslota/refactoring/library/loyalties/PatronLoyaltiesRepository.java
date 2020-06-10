package com.bslota.refactoring.library.loyalties;

import java.util.Optional;

/**
 * @author bslota on 06/06/2020
 */
interface PatronLoyaltiesRepository {

    Optional<PatronLoyalties> findBy(PatronId patronId);

    void update(PatronLoyalties patron);
}
