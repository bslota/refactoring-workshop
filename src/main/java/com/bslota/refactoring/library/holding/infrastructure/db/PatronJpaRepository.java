package com.bslota.refactoring.library.holding.infrastructure.db;

import org.springframework.data.jpa.repository.JpaRepository;

interface PatronJpaRepository extends JpaRepository<PatronEntity, Integer> {
}
