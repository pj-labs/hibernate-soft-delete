package com.pj.hibernate.soft.delete.service;

import com.pj.hibernate.soft.delete.domain.Book;
import com.pj.hibernate.soft.delete.dto.BookInfo;
import com.pj.hibernate.soft.delete.repository.EmProvider;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * A service class that handles Business Logic related to Book operations.
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@Service
public class BookServiceImpl implements BookService {
    /**
     * Find all Books in the database.
     *
     * @return list of Books or an empty list if no Books are found
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    public List<BookInfo> findAll() {
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        List<BookInfo> books = em.createQuery("SELECT new com.pj.hibernate.soft.delete.dto.BookInfo(b.id, b.title, b.isbn, b.edition, b.yearOfPublication," +
                " b.publisher) FROM Book b WHERE b.isbn IS NOT NULL", BookInfo.class).getResultList();
        em.close();
        return books;
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
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        Book book = new Book();
        book.setTitle("Spring Boot 2 Recipes");
        book.setIsbn("978-1-4842-3925-4");
        book.setEdition(1);
        book.setYearOfPublication(2018);
        book.setPublisher("OReilly Media");
        em.persist(book);
        em.getTransaction().commit();
        em.close();
        return book;
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
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        if (book != null) {
            book.setTitle("Spring Boot 3 Recipes");
            book.setEdition(2);
            book.setYearOfPublication(2019);
            book.setPublisher("Personal Publication");
        }
        em.getTransaction().commit();
        em.close();
        return book;
    }

    @Override
    public void deleteBook(Long id) {
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
        em.getTransaction().commit();
        em.close();
    }
}