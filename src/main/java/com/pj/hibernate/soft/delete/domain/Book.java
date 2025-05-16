package com.pj.hibernate.soft.delete.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "book")
@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String isbn;
    private Integer edition;
    private Integer yearOfPublication;
    private String publisher;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(name = "book_author",
               joinColumns = @JoinColumn(name = "book_id"),
               inverseJoinColumns = @JoinColumn(name = "author_id"))
    @JsonManagedReference
    private List<Author> authors = new ArrayList<>();

    @Override
    public int hashCode() {
        return Objects.hash(id, title, isbn, edition, yearOfPublication, publisher);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title) && Objects.equals(isbn, book.isbn) &&
                Objects.equals(edition, book.edition) && Objects.equals(yearOfPublication, book.yearOfPublication) &&
                Objects.equals(publisher, book.publisher);
    }

    @Override
    public String toString() {
        return "Book{" +
                "publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", edition=" + edition +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}