package com.dongjin.oad.util;

import org.junit.Test;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DataSourceImplOnlyConnectionTest {

    @Test
    public void getConnection() throws SQLException{

        String url = "jdbc:mysql://localhost:3306/dongjin?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
        String id = "root";
        String pw = "password1";

        DataSource dataSourceImplOnlyConnection = new DataSourceImplOnlyConnection(url, id, pw);
        Connection connection = dataSourceImplOnlyConnection.getConnection();

        assertNotNull(connection);

        assertEquals(false, dataSourceImplOnlyConnection.getConnection().isClosed());

        connection.close();
        assertEquals(true, connection.isClosed());

        dataSourceImplOnlyConnection.getConnection();
        assertEquals(true, connection.isClosed());
    }
}