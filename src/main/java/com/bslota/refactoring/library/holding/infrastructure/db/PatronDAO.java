package com.bslota.refactoring.library.holding.infrastructure.db;

import com.bslota.refactoring.library.holding.infrastructure.db.BookEntity;
import com.bslota.refactoring.library.holding.infrastructure.db.PatronEntity;
import com.bslota.refactoring.library.holding.model.Patron;
import com.bslota.refactoring.library.holding.model.PatronId;
import com.bslota.refactoring.library.holding.model.PatronRepository;
import com.bslota.refactoring.library.holding.infrastructure.db.BookJpaRepository;
import com.bslota.refactoring.library.holding.infrastructure.db.PatronJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toList;

@Repository
class PatronDAO implements PatronRepository {

    @Autowired
    private PatronJpaRepository patronRepository;

    @Autowired
    private BookJpaRepository bookRepository;

    public Patron getPatronFromDatabase(int patronId) {
        return patronRepository.findById(patronId).map(this::mapToModel).orElse(null);
    }

    @Override
    public Optional<Patron> findBy(PatronId patronId) {
        return Optional.ofNullable(patronId)
                .map(PatronId::asInt)
                .map(this::getPatronFromDatabase);
    }

    @Override
    @Transactional
    public void update(Patron patron) {
        PatronEntity patronEntity = patronRepository.findById(patron.getPatronId().asInt()).orElseThrow(() -> new IllegalArgumentException("Patron does not exist"));
        patronEntity.setHolds(patron.getHolds().stream().map(id -> bookRepository.findById(id).orElse(null)).filter(Objects::nonNull).collect(Collectors.toSet()));
        patronRepository.save(patronEntity);
    }

    private Patron mapToModel(PatronEntity entity) {
        return new Patron(
                PatronId.of(entity.getPatronId()),
                Optional.ofNullable(entity.getHolds())
                        .orElse(emptySet())
                        .stream()
                        .map(BookEntity::getBookId).collect(toList()));
    }
}
