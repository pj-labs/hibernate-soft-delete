package com.pj.hibernate.soft.delete.service;

import com.pj.hibernate.soft.delete.domain.Author;
import com.pj.hibernate.soft.delete.domain.AuthorLog;
import com.pj.hibernate.soft.delete.repository.AuthorLogRepository;
import com.pj.hibernate.soft.delete.repository.AuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private static final Logger logger = LoggerFactory.getLogger(AuthorServiceImpl.class);
    private final AuthorRepository authorRepository;
    private final AuthorLogRepository authorLogRepository;
    private final AuthorServiceImpl self;

    public AuthorServiceImpl(AuthorRepository authorRepository, AuthorLogRepository authorLogRepository, @Lazy AuthorServiceImpl self) {
        this.authorRepository = authorRepository;
        this.authorLogRepository = authorLogRepository;
        this.self = self;
    }

    @Override
    public void delete(String email) {
        Author author = authorRepository.findByEmail(email);
        authorRepository.delete(author);
        self.saveLog();
    }

    @Override
    public void updateTransactional() {
        var authors = authorRepository.findAll();
        for (var author : authors) {
            saveAuthors(author);
        }
    }

    /**
     * Create a new Author and persist it to the database.
     *
     * @return the newly created Author
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    public Author createNewAuthor() {
        var author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("jdoe2@example.com");
        author.setPhoneNumber("1234567890");
        return authorRepository.save(author);
    }

    /**
     * Update the Author and persist it to the database.
     *
     * @param email the email of the Author to be updated
     *
     * @return the updated Author
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    public Author update(String email) {
        var author = authorRepository.findByEmail(email);
        if (author != null) {
            author.setFirstName("John");
            author.setLastName("Doe");
            author.setEmail("jdoe2@example.com");
            author.setPhoneNumber("1234567890");
            author = authorRepository.saveAndFlush(author);
            saveLog(author.getFirstName(), author.getLastName(), author.getEmail(), author.getPhoneNumber());
        }
        return author;
    }

    /**
     * Find all Authors in the database.
     *
     * @return list of Authors or an empty list if no Authors are found
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    public void saveAuthors(Author author) {
        author.setFirstName("transactional");
        authorRepository.save(author);
        throw new RuntimeException("Transactional");
    }

    @Async
    public void saveLog() {
        logger.info("Saved log {}", Thread.currentThread().getName());
    }

    public void saveLog(String firstName, String lastName, String email, String phoneNumber) {
        var authorLog = new AuthorLog();
        authorLog.setFirstName(firstName);
        authorLog.setLastName(lastName);
        authorLog.setEmail(email);
        authorLog.setPhoneNumber(phoneNumber);
        authorLogRepository.saveAndFlush(authorLog);
        logger.info("Saved authorLog in thread:{}", Thread.currentThread().getName());
    }
}