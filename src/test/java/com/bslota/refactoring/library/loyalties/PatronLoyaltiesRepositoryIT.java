package com.bslota.refactoring.library.loyalties;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.bslota.refactoring.library.holding.fixture.NumericFixture.randomInt;
import static com.bslota.refactoring.library.loyalties.PatronLoyaltiesFixture.randomNumberOfPoints;
import static org.apache.commons.lang3.RandomUtils.nextBoolean;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PatronLoyaltiesRepositoryIT {

    private static final int REGULAR_PATRON_TYPE = 0;

    @Autowired
    private PatronLoyaltiesJpaRepository repository;

    @Test
    void shouldGetCreatedPatron() {
        PatronLoyaltiesEntity patronLoyaltiesEntity = new PatronLoyaltiesEntity(randomInt(),
                REGULAR_PATRON_TYPE, randomNumberOfPoints(), nextBoolean());
        repository.save(patronLoyaltiesEntity);

        PatronLoyaltiesEntity foundLoyalties = repository.findById(patronLoyaltiesEntity.getPatronId()).get();

        assertEquals(patronLoyaltiesEntity.getPatronType(), foundLoyalties.getPatronType());
        assertEquals(patronLoyaltiesEntity.getPoints(), foundLoyalties.getPoints());
        assertEquals(patronLoyaltiesEntity.isQualifiesForFreeBook(), foundLoyalties.isQualifiesForFreeBook());
    }
}