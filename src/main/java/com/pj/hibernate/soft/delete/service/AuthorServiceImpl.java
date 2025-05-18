package com.pj.hibernate.soft.delete.service;

import com.pj.hibernate.soft.delete.domain.Author;
import com.pj.hibernate.soft.delete.repository.EmProvider;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

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
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        List<Author> authors = em.createQuery("SELECT a FROM Author a", Author.class).getResultList();
        em.getTransaction().commit();
        em.close();
        return authors;
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
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");
        author.setEmail("jdoe2@example.com");
        author.setPhoneNumber("1234567890");
        em.persist(author);
        em.getTransaction().commit();
        em.close();
        return author;
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
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        Author author = em.createQuery("SELECT a FROM Author a WHERE a.email = :email", Author.class).setParameter("email", email).getResultStream().findFirst()
                .orElse(null);
        if (author != null) {
            author.setFirstName("John");
            author.setLastName("Doe");
            author.setEmail("jdoe2@example.com");
            author.setPhoneNumber("1234567890");
            em.merge(author);
        }
        em.getTransaction().commit();
        em.close();
        return author;
    }

    @Override
    public void delete(String email) {
        var em = EmProvider.getEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT a FROM Author a WHERE a.email = :email", Author.class).setParameter("email", email).getResultStream().findFirst()
                .ifPresent(em::remove);
        em.getTransaction().commit();
        em.close();
    }
}