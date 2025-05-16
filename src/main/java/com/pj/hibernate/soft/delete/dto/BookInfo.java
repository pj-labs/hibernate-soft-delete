package com.pj.hibernate.soft.delete.dto;

/**
 * Projection for {@link com.pj.hibernate.soft.delete.domain.Book}
 */
public interface BookInfo {
    Long getId();

    String getTitle();

    String getIsbn();

    Integer getEdition();

    Integer getYearOfPublication();

    String getPublisher();
}