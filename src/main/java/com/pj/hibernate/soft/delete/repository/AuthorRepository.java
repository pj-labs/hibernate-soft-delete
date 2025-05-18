package com.pj.hibernate.soft.delete.repository;

import com.pj.hibernate.soft.delete.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findByEmail(String email);
}