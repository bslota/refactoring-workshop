package com.bslota.refactoring.library.repository;

import com.bslota.refactoring.library.entity.BookEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookRepositoryIT {

    @Autowired
    private BookJpaRepository bookRepository;

    @Test
    void shouldGetCreatedBook() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(1);
        bookEntity.setAuthor("Rob C. Martin");
        bookEntity.setTitle("Clean Architecture");
        bookEntity.setType("CIRCULATING");
        bookRepository.save(bookEntity);

        BookEntity foundBook = bookRepository.findById(1).get();

        assertEquals(bookEntity.getTitle(), foundBook.getTitle());
        assertEquals(bookEntity.getAuthor(), foundBook.getAuthor());
        assertEquals(bookEntity.getType(), foundBook.getType());
    }
}