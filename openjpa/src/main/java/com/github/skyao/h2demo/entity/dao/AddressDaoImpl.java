package com.github.skyao.h2demo.entity.dao;

import com.github.skyao.h2demo.entity.AddressEntity;

/**
 * Created by sky on 16-1-18.
 */
public class AddressDaoImpl extends AbstractDao implements AddressDao {

    public int save(AddressEntity addressEntity) {
        entityManager.persist(addressEntity);
        return addressEntity.getId();
    }

    public AddressEntity query(int id) {
        return entityManager.find(AddressEntity.class, id);
    }
}
