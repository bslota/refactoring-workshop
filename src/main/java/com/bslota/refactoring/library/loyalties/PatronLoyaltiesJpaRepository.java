package com.bslota.refactoring.library.loyalties;

import org.springframework.data.jpa.repository.JpaRepository;

interface PatronLoyaltiesJpaRepository extends JpaRepository<PatronLoyaltiesEntity, Integer> {
}
