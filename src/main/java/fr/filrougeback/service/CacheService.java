package fr.filrougeback.service;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    private EntityManager entityManager;

    public void clearSessionCache() {
        entityManager.unwrap(Session.class).clear();
    }
}
