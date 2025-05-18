package com.pj.hibernate.soft.delete.service;

import com.pj.hibernate.soft.delete.domain.Author;
import com.pj.hibernate.soft.delete.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
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
        }
        return author;
    }

    @Override
    public void delete(String email) {
        Author author = authorRepository.findByEmail(email);
        authorRepository.delete(author);
    }
}