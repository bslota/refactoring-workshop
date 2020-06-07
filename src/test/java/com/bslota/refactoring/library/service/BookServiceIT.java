package com.bslota.refactoring.library.service;

import com.bslota.refactoring.library.entity.BookEntity;
import com.bslota.refactoring.library.entity.PatronEntity;
import com.bslota.refactoring.library.repository.BookJpaRepository;
import com.bslota.refactoring.library.repository.PatronJpaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookServiceIT {

    @Autowired
    BookJpaRepository bookRepository;

    @Autowired
    PatronJpaRepository patronRepository;

    @Autowired
    PlacingOnHold bookService;

    @Test
    void shouldPlaceBookOnHold() {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setBookId(1);
        bookEntity.setAuthor("Rob C. Martin");
        bookEntity.setTitle("Clean Architecture");
        bookEntity.setType("CIRCULATING");
        bookRepository.save(bookEntity);

        PatronEntity patronEntity = new PatronEntity();
        patronEntity.setPatronId(1);
        patronEntity.setType(0);
        patronEntity.setPoints(0);
        patronRepository.save(patronEntity);

        boolean result = bookService.placeOnHold(1, 1, 100);

        assertTrue(result);

        BookEntity bookAfterHolding = bookRepository.findById(1).get();
        assertNotNull(bookAfterHolding.getReservationDate());
        assertNotNull(bookAfterHolding.getReservationEndDate());
        assertEquals(1, bookAfterHolding.getPatronEntity().getPatronId());

        PatronEntity patronAfterHolding = patronRepository.findById(1).get();
        assertEquals(1, patronAfterHolding.getHolds().size());
    }
}
