package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.PatronLoyaltiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronLoyaltiesJpaRepository extends JpaRepository<PatronLoyaltiesEntity, Integer> {
}
