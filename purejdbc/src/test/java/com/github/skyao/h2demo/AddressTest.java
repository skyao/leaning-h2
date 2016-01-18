package com.github.skyao.h2demo;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sky on 16-1-18.
 */
public class AddressTest extends H2TestBase {

    private Connection connection;

    @Before
    public void prepareConnection() throws SQLException {
        connection = dataSource.getConnection();
    }

    @Test
    public void testPureJdbc() throws Exception {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO address(id, name) VALUES(1, 'Miller');");
        assertThat(statement.executeUpdate()).isEqualTo(1);

        statement = connection.prepareStatement("select id, name from address");
        ResultSet executeQuery = statement.executeQuery();
        executeQuery.next();
        int id = executeQuery.getInt(1);
        String name = executeQuery.getString(2);

        assertThat(id).isEqualTo(1);
        assertThat(name).isEqualTo("Miller");

        System.out.println("id=" + id + ", name=" + name);
    }
}
