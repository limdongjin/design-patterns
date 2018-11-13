package com.dongjin.oad.util;

import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class DataSourceImplOnlyConnectionTest {

    @Test
    public void getConnection() throws SQLException{
        DataSourceImplOnlyConnection dataSourceImplOnlyConnection = new DataSourceImplOnlyConnection();

        String url = "jdbc:mysql://localhost:3306/dongjin?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
        String id = "root";
        String pw = "password1";

        try {
            dataSourceImplOnlyConnection.setConnection(url, id, pw);
        }catch (SQLException e){
            throw e;
        }

        assertNotNull(dataSourceImplOnlyConnection.getConnection());
    }
}