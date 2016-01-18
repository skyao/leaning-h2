package com.github.skyao.h2demo.entity.dao;

import com.github.skyao.h2demo.entity.AddressEntity;

/**
 * Created by sky on 16-1-18.
 */
public interface AddressDao {

    int save(AddressEntity addressEntity);

    AddressEntity query(int id);
}
