package com.bslota.refactoring.library.holding.infrastructure.db;

import com.bslota.refactoring.library.holding.infrastructure.db.BookEntity;
import com.bslota.refactoring.library.holding.infrastructure.db.PatronEntity;
import com.bslota.refactoring.library.holding.model.Book;
import com.bslota.refactoring.library.holding.model.BookId;
import com.bslota.refactoring.library.holding.model.BookRepository;
import com.bslota.refactoring.library.holding.infrastructure.db.BookJpaRepository;
import com.bslota.refactoring.library.holding.infrastructure.db.PatronJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static java.util.Optional.ofNullable;

@Repository
class BookDAO implements BookRepository {

    @Autowired
    private BookJpaRepository bookRepository;

    @Autowired
    private PatronJpaRepository patronRepository;

    @Override
    public Optional<Book> findBy(BookId bookId) {
        return Optional.ofNullable(bookId)
                .map(BookId::asInt)
                .map(this::getBookFromDatabase);
    }

    @Override
    @Transactional
    public void update(Book book) {
        bookRepository.save(mapToEntity(book));
    }

    public Book getBookFromDatabase(int bookId) {
        return bookRepository.findById(bookId).map(this::mapToModel).orElse(null);
    }

    private BookEntity mapToEntity(Book book) {
        BookEntity bookEntity = bookRepository.findById(book.getBookId().asInt()).orElseThrow(() -> new IllegalArgumentException("Book does not exist!"));
        PatronEntity patronEntity = Optional.ofNullable(book.getPatronId())
                .flatMap(patronRepository::findById).orElse(null);
        bookEntity.setReservationDate(book.getReservationDate());
        bookEntity.setReservationEndDate(book.getReservationEndDate());
        bookEntity.setPatronEntity(patronEntity);
        return bookEntity;
    }

    private Book mapToModel(BookEntity entity) {
        return new Book(
                BookId.of(entity.getBookId()), entity.getReservationDate(),
                entity.getReservationEndDate(),
                entity.getType(),
                ofNullable(entity.getPatronEntity()).map(PatronEntity::getPatronId).orElse(null));
    }
}
