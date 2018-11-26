package com.dongjin.oad.dao;

import com.dongjin.oad.dto.Product;

import java.sql.SQLException;
import java.util.Optional;
import java.util.stream.Stream;

public interface ProductDao {
    /**
     * @return all products as a stream.
     * @throws Exception
     */
    Stream findAll() throws SQLException, IllegalAccessException;

    /**
     * @param id
     * @return Optional product
     * @throws Exception
     */
    Optional<Product> findById(int id) throws SQLException, IllegalAccessException;

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    boolean create(Product product) throws SQLException, IllegalAccessException;

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    boolean update(Product product) throws SQLException, IllegalAccessException;

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    boolean delete(Product product) throws SQLException, IllegalAccessException;

}