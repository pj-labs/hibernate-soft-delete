package com.pj.hibernate.soft.delete.repository;

import com.pj.hibernate.soft.delete.domain.AuthorLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorLogRepository extends JpaRepository<AuthorLog, Long> {
}