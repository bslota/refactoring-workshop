package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.PatronEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class PatronRepositoryIT {

    @Autowired
    private PatronJpaRepository patronRepository;

    @Test
    void shouldGetCreatedPatron() {
        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setPatronId(1);
        patronEntity.setType(0);
        patronEntity.setPoints(0);
        patronRepository.save(patronEntity);

        PatronEntity foundPatron = patronRepository.findById(1).get();

        assertEquals(patronEntity.getType(), foundPatron.getType());
        assertEquals(patronEntity.getPoints(), foundPatron.getPoints());
    }
}