package com.bslota.refactoring.library.dao;

import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.entity.BookEntity;
import com.bslota.refactoring.library.entity.PatronEntity;
import com.bslota.refactoring.library.repository.BookRepository;
import com.bslota.refactoring.library.repository.PatronRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

@Repository
public class PatronDAO {

    @Autowired
    private PatronRepository patronRepository;

    @Autowired
    private BookRepository bookRepository;

    public Patron getPatronFromDatabase(int patronId) {
        return patronRepository.findById(patronId).map(this::mapToModel).orElse(null);
    }

    @Transactional
    public void update(Patron patron) {
        PatronEntity patronEntity = patronRepository.findById(patron.getPatronIdValue()).orElseThrow(() -> new IllegalArgumentException("Patron does not exist"));
        patronEntity.setPoints(patron.getPoints());
        patronEntity.setQualifiesForFreeBook(patron.isQualifiesForFreeBook());
        patronEntity.setHolds(patron.getHolds().stream().map(id -> bookRepository.findById(id).orElse(null)).filter(Objects::nonNull).collect(Collectors.toSet()));
        patronRepository.save(patronEntity);
    }

    private Patron mapToModel(PatronEntity entity) {
        return new Patron(entity.getPatronId(),
                entity.getType(),
                entity.getPoints(),
                entity.isQualifiesForFreeBook(),
                Optional.ofNullable(entity.getHolds())
                        .orElse(emptySet())
                        .stream()
                        .map(BookEntity::getBookId).collect(toList()));
    }
}
