package pl.patrykgala.ee.testing.jpa;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.Typed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.testcontrol.api.mock.ApplicationMockManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;

public class AbstractEntityManagerTest {

    protected static EntityManagerFactory emf;
    protected static EntityManager em;

    @BeforeClass
    public static void init() {
        emf = Persistence.createEntityManagerFactory("mnf-pu-test");
        em = emf.createEntityManager();
        ApplicationMockManager applicationMockManager = BeanProvider.getContextualReference(
                ApplicationMockManager.class);
        applicationMockManager.addMock(new MockedEntityManagerProducer(em));
    }

    @AfterClass
    public static void tearDown() {
        em.clear();
        em.close();
        emf.close();
    }

    public static class MockedEntityManagerProducer extends EntityManagerProducer {

        public MockedEntityManagerProducer() {
        }

        public MockedEntityManagerProducer(EntityManager em) {
            this.em = em;
        }

        @Produces
        @Typed
        @Override
        public EntityManager supply() {
            return em;
        }
    }
}
