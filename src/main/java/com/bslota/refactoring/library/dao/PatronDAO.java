package com.bslota.refactoring.library.dao;

import com.bslota.refactoring.library.model.Patron;
import com.bslota.refactoring.library.entity.BookEntity;
import com.bslota.refactoring.library.entity.PatronEntity;
import com.bslota.refactoring.library.model.PatronId;
import com.bslota.refactoring.library.model.PatronLoyalties;
import com.bslota.refactoring.library.repository.BookJpaRepository;
import com.bslota.refactoring.library.repository.PatronJpaRepository;
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
    private PatronJpaRepository patronRepository;

    @Autowired
    private BookJpaRepository bookRepository;

    public Patron getPatronFromDatabase(int patronId) {
        return patronRepository.findById(patronId).map(this::mapToModel).orElse(null);
    }

    @Transactional
    public void update(Patron patron) {
        PatronEntity patronEntity = patronRepository.findById(patron.getPatronId().asInt()).orElseThrow(() -> new IllegalArgumentException("Patron does not exist"));
        patronEntity.setPoints(patron.getPatronLoyalties().getPoints());
        patronEntity.setQualifiesForFreeBook(patron.getPatronLoyalties().isQualifiesForFreeBook());
        patronEntity.setHolds(patron.getHolds().stream().map(id -> bookRepository.findById(id).orElse(null)).filter(Objects::nonNull).collect(Collectors.toSet()));
        patronRepository.save(patronEntity);
    }

    private Patron mapToModel(PatronEntity entity) {
        return new Patron(
                PatronId.of(entity.getPatronId()),
                Optional.ofNullable(entity.getHolds())
                        .orElse(emptySet())
                        .stream()
                        .map(BookEntity::getBookId).collect(toList()), new PatronLoyalties(entity.getPatronId(), entity.getType(), entity.getPoints(), entity.isQualifiesForFreeBook()));
    }
}
