package com.pj.hibernate.soft.delete.web;

import com.pj.hibernate.soft.delete.domain.Author;
import com.pj.hibernate.soft.delete.service.AuthorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Provides a REST API endpoints for the Author entity.
 *
 * @author Pavan Kumar Jadda
 * @since 1.0.0
 */
@RestController
@RequestMapping("/api/v1/author")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Find all Authors in the database.
     *
     * @return list of Authors or an empty list if no Authors are found
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @GetMapping("/find/all")
    public List<Author> findAll() {
        return authorService.findAll();
    }

    /**
     * Create a new Author and persist it to the database.
     *
     * @return the newly created Author
     *
     * @author Pavan Kumar Jadda
     * @since 1.0.0
     */
    @GetMapping("/create")
    public Author createNewAuthor() {
        return authorService.createNewAuthor();
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
    @GetMapping("/update/{email}")
    public Author update(@PathVariable String email) {
        return authorService.update(email);
    }
}