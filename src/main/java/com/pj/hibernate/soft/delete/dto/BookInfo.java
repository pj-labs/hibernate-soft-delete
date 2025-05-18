package com.pj.hibernate.soft.delete.dto;

/**
 * Projection for {@link com.pj.hibernate.soft.delete.domain.Book}
 */
public record BookInfo(Long id, String title, String isbn, Integer edition, Integer yearOfPublication, String publisher) {
}