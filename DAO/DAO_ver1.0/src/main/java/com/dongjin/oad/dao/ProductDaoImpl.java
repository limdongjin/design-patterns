package com.dongjin.oad.dao;

import com.dongjin.oad.dto.Product;
import com.dongjin.oad.util.Action;
import com.dongjin.oad.util.JdbcContext;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class ProductDaoImpl implements ProductDao {

    JdbcContext jdbcContext;

    public ProductDaoImpl(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    /**
     * @return all products as a stream.
     * @throws Exception
     */
    @Override
    public Stream<Product> findAll() throws Exception {
        ArrayList products;

        products = jdbcContext.executeSql("SELECT * FROM products", (Action) resultSet1 -> {
            ArrayList<Product> products1 = new ArrayList<>();
            while (resultSet1.next()){
                System.out.println("0");

                products1.add(new Product(resultSet1.getInt("id"),
                        resultSet1.getString("name"),
                        resultSet1.getString("description"),
                        resultSet1.getInt("price")));
            }

            return products1;
        });

        return products.stream();
    }

    /**
     * @param id
     * @return Optional product
     * @throws Exception
     */
    @Override
    public Optional<Product> findById(int id) throws Exception {
        return Optional.empty();
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean create(Product product) throws Exception {
        return false;
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean update(Product product) throws Exception {
        return false;
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean delete(Product product) throws Exception {
        return false;
    }
}
