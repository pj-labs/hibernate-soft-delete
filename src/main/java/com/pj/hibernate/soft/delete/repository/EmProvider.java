package com.pj.hibernate.soft.delete.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.springframework.stereotype.Component;

@Component
public class EmProvider {
    private static final EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("appPu");
        } catch (Exception e) {
            throw new RuntimeException("Error initializing EntityManagerFactory", e);
        }
    }

    private EmProvider() {
        // Private constructor to prevent instantiation
    }

    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public static void beginTransaction(EntityManager em) {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    public static void commitTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    public static void rollbackTransaction(EntityManager em) {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }
    }

    public static void closeEntityManager(EntityManager em) {
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public static void shutdown() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}