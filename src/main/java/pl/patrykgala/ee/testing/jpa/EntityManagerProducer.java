package pl.patrykgala.ee.testing.jpa;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class EntityManagerProducer {

    /*
     * EntityManager for production
     */
    @PersistenceContext
    protected EntityManager em;

    @Produces
    public EntityManager supply() {
        return em;
    }
}
