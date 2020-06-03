package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.PatronEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatronRepository extends JpaRepository<PatronEntity, Integer> {
}
