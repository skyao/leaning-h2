package com.github.skyao.h2demo;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by sky on 16-1-18.
 */
public class AbstractH2DaoTest {

    private static EntityManagerFactory emf;

    protected EntityManager entityManager;

    @Before
    public void prepareH2() throws Exception {
        if (emf == null) {
            try {
                emf = Persistence.createEntityManagerFactory("EmbeddedH2");
            } catch (Throwable e) {
                System.out.printf("******  fail to initial openJpa and H2    *******");
                e.printStackTrace();
                Assert.fail("fail to initial openJpa and H2");
            }
        }

        entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
    }

    @After
    public void tearDown() {
        if (entityManager != null) {
            entityManager.getTransaction().commit();
            entityManager = null;
        }
    }
}
