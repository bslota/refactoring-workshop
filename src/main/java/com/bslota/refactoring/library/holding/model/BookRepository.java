package com.bslota.refactoring.library.holding.model;

import java.util.Optional;

/**
 * @author bslota on 06/06/2020
 */
public interface BookRepository {

    Optional<Book> findBy(BookId bookId);

    void update(Book book);
}
