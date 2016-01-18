package com.github.skyao.h2demo.dao;

import com.github.skyao.h2demo.AbstractH2DaoTest;
import com.github.skyao.h2demo.entity.AddressEntity;
import com.github.skyao.h2demo.entity.dao.AddressDaoImpl;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sky on 16-1-18.
 */
public class AddressDaoImplTest extends AbstractH2DaoTest {

    private AddressDaoImpl dao;

    @Before
    public void prepareDao() {
        dao = new AddressDaoImpl();
        dao.setEntityManager(this.entityManager);
    }

    @Test
    public void testSaveAndQuery() {
        AddressEntity addressEntity =new AddressEntity();
        addressEntity.setId(100);
        addressEntity.setName("sky");
        int id = dao.save(addressEntity);
        //System.out.println(addressEntity.toString());

        AddressEntity queryEntity = dao.query(id);
        //System.out.println(queryEntity.toString());
        assertThat(queryEntity).isEqualToComparingFieldByField(addressEntity);
    }
}
