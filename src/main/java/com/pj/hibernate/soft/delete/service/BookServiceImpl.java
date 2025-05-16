package com.pj.hibernate.soft.delete.service;

import com.pj.hibernate.soft.delete.domain.Author;
import com.pj.hibernate.soft.delete.domain.Book;
import com.pj.hibernate.soft.delete.dto.BookInfo;
import com.pj.hibernate.soft.delete.repository.AuthorRepository;
import com.pj.hibernate.soft.delete.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class that handles Business Logic related to Book operations.
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Service
@Transactional
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    /**
     * Find all Books in the database.
     *
     * @return list of Books or an empty list if no Books are found
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    @Transactional
    public List<BookInfo> findAll() {
        return bookRepository.findAllByIsbnIsNotNull();
    }

    /**
     * Create a new Book and persist it to the database.
     *
     * @return the newly created Book
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    public Book createNewBook() {
        Book book = new Book();
        book.setTitle("Spring Boot 2 Recipes");
        book.setIsbn("978-1-4842-3925-4");
        book.setEdition(1);
        book.setYearOfPublication(2018);
        book.setPublisher("OReilly Media");
        return bookRepository.save(book);
    }

    /**
     * Create a new Book and persist it to the database.
     *
     * @return the newly created Book
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    public Book updateBook(Long id) {
        var book = bookRepository.getReferenceById(id);
        book.setTitle("Spring Boot 3 Recipes");
        book.setEdition(2);
        book.setYearOfPublication(2019);
        book.setPublisher("Personal Publication");
        var author = authorRepository.save(new Author("John", "Doe", "jdoe@example.com", "123-456-7890"));
        book.getAuthors().add(author);
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}