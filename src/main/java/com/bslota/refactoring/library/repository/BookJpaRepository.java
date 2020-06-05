package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookJpaRepository extends JpaRepository<BookEntity, Integer> {
}
