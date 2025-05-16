package com.pj.hibernate.soft.delete.repository;

import com.pj.hibernate.soft.delete.domain.Book;
import com.pj.hibernate.soft.delete.dto.BookInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<BookInfo> findAllIsbnIsNotNull();
}