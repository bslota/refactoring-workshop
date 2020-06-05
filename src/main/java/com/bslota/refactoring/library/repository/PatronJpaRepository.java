package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronJpaRepository extends JpaRepository<PatronEntity, Integer> {
}
