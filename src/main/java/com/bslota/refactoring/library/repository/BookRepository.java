package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
