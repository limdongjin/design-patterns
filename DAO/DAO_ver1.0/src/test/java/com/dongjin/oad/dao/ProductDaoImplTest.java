package com.dongjin.oad.dao;

import com.dongjin.oad.dto.Product;
import com.dongjin.oad.util.DataSourceImplOnlyConnection;
import com.dongjin.oad.util.JdbcContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class ProductDaoImplTest {

    private ProductDao productDao;
    private String  id;
    private String pw;
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
        assertEquals(true, productDao.create(product1));
        assertEquals(true, product1.equals(productDao.findById(21).get()));
        assertNotEquals(true, product1 == productDao.findById(21).get());
    }

    @Test
    public void create() throws SQLException, IllegalAccessException {
        assertEquals(true, productDao.create(new Product(11, "2", "111", 111)));
    }

    @Test
    public void update() throws SQLException, IllegalAccessException {
        productDao.create(new Product(1, "ehlll","sdfsdfsd" , 100));
        assertEquals(true, productDao.update(new Product(1, "2", "222", 11)));
    }

    @Test
    public void delete() throws SQLException, IllegalAccessException {
        Product product =  new Product(20000, "hello!!!!", "world", 404);
        productDao.create(product);
        assertEquals(true, productDao.delete(product));
        assertEquals(Optional.empty(), productDao.findById(20000));
    }
}