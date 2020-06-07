package com.bslota.refactoring.library.dao;

import com.bslota.refactoring.library.entity.PatronLoyaltiesEntity;
import com.bslota.refactoring.library.model.PatronId;
import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.model.PatronLoyaltiesRepository;
import com.bslota.refactoring.library.repository.PatronLoyaltiesJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author bslota on 07/06/2020
 */
@Repository
class PatronLoyaltiesDAO implements PatronLoyaltiesRepository {

    private final PatronLoyaltiesJpaRepository jpaRepository;

    PatronLoyaltiesDAO(PatronLoyaltiesJpaRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public Optional<PatronLoyalties> findBy(PatronId patronId) {
        return Optional.ofNullable(patronId)
                .map(PatronId::asInt)
                .flatMap(jpaRepository::findById)
                .map(this::mapToModel);
    }

    @Override
    public void update(PatronLoyalties loyalties) {
        PatronLoyaltiesEntity entity = new PatronLoyaltiesEntity(loyalties.getPatronId().asInt(),
                loyalties.getType(), loyalties.getPoints(), loyalties.isQualifiesForFreeBook());
        jpaRepository.save(entity);
    }

    private PatronLoyalties mapToModel(PatronLoyaltiesEntity entity) {
        return new PatronLoyalties(PatronId.of(entity.getPatronId()),
                entity.getPatronType(), entity.getPoints(), entity.isQualifiesForFreeBook());
    }
}
