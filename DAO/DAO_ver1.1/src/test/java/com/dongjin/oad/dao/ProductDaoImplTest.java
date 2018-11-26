package com.dongjin.oad.dao;

import com.dongjin.oad.dto.Product;
import com.dongjin.oad.util.DataSourceImplOnlyConnection;
import com.dongjin.oad.util.JdbcContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.Optional;

import static org.junit.Assert.*;

public class ProductDaoImplTest {

    private ProductDao productDao;
// --Commented out by Inspection START (26/11/2018, 11:29 AM):
//    // --Commented out by In// --Commented out by Inspection (26/11/2018, 11:27 AM):spection (26/11/2018, 11:27 AM):private String  id;
//    private String pw;
// --Commented out by Inspection STOP (26/11/2018, 11:29 AM)
    private String url;

    private void setProductDao() throws SQLException {
        url = "jdbc:sqlite:test.db";

        productDao = new ProductDaoImpl(new JdbcContext(new DataSourceImplOnlyConnection(url)));
    }

    @Before
    public void setUp() throws Exception {
        setProductDao();

        new JdbcContext(new DataSourceImplOnlyConnection(url)).executeSql(
                "create table products ( id INTEGER not Null, name VARCHAR(255), description VARCHAR(255), price INTEGER )"
       );
    }

    @After
    public void tearDown() throws Exception {
        new JdbcContext(new DataSourceImplOnlyConnection(url)).executeSql(
                "drop table products"
        );
    }



    @Test
    public void findAll() throws SQLException, IllegalAccessException {
        assertNotNull(productDao.findAll());
    }

    @Test
    public void findById() throws SQLException, IllegalAccessException {

        assertNotEquals(false, productDao.create(new Product(1, "11", "ddd", 111)));
        assertNotEquals( productDao.findById(1), Optional.empty());

        assertNotEquals(false, productDao.delete(new Product(1, "11", "ddd", 111)));
        assertEquals(productDao.findById(1), Optional.empty());

        Product product1 = new Product(21, "hello world", "hi!!!하이", 23011);
        assertTrue(productDao.create(product1));
        assertEquals(product1, productDao.findById(21).get());
        assertNotEquals(true, product1 == productDao.findById(21).get());
    }

    @Test
    public void create() throws SQLException, IllegalAccessException {
        assertTrue(productDao.create(new Product(11, "2", "111", 111)));
    }

    @Test
    public void update() throws SQLException, IllegalAccessException {
        productDao.create(new Product(1, "ehlll","sdfsdfsd" , 100));
        assertTrue(productDao.update(new Product(1, "2", "222", 11)));
    }

    @Test
    public void delete() throws SQLException, IllegalAccessException {
        Product product =  new Product(20000, "hello!!!!", "world", 404);
        productDao.create(product);
        assertTrue(productDao.delete(product));
        assertEquals(Optional.empty(), productDao.findById(20000));
    }
}