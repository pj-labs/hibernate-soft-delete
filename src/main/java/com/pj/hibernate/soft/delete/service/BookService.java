package com.pj.hibernate.soft.delete.service;

import com.pj.hibernate.soft.delete.domain.Book;
import com.pj.hibernate.soft.delete.dto.BookInfo;

import java.util.List;

public interface BookService {
    /**
     * Find all Books in the database.
     *
     * @return list of Books or an empty list if no Books are found
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    List<BookInfo> findAll();

    /**
     * Create a new Book and persist it to the database.
     *
     * @return the newly created Book
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    Book createNewBook();

    /**
     * Create a new Book and persist it to the database.
     *
     * @return the newly created Book
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    Book updateBook(Long id);

    void deleteBook(Long id);
}