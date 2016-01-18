package com.github.skyao.h2demo.entity.dao;

import javax.persistence.EntityManager;

/**
 * Created by sky on 16-1-18.
 */
public abstract class AbstractDao  {

    protected EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
