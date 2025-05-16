package com.pj.hibernate.soft.delete.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Entity
@Table(name = "author_log")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class AuthorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof AuthorLog authorLog)) return false;
        return Objects.equals(id, authorLog.id) && Objects.equals(firstName, authorLog.firstName) &&
                Objects.equals(lastName, authorLog.lastName) && Objects.equals(email, authorLog.email) &&
                Objects.equals(phoneNumber, authorLog.phoneNumber);
    }
}