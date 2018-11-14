package com.dongjin.oad.dao;

import com.dongjin.oad.dto.Product;
import com.dongjin.oad.util.Action;
import com.dongjin.oad.util.JdbcContext;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Optional;
import java.util.stream.Stream;

public class ProductDaoImpl implements ProductDao {

    JdbcContext jdbcContext;

    DataSource dataSource;

    public ProductDaoImpl(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public void setJdbcContext(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    private void reConnection(){

    }
    /**
     * @return all products as a stream.
     * @throws Exception
     */
    @Override
    public Stream<Product> findAll() throws SQLException, IllegalAccessException {
        ArrayList products;
        String query = generateQuery(null, "selectAll");

        products = jdbcContext.executeSql(query, (Action) resultSet1 -> {
            ArrayList<Product> products1 = new ArrayList<>();
            while (resultSet1.next()){
                products1.add(resultSetToProduct(resultSet1));
            }

            return products1;
        });

        return products.stream();
    }

    /**
     * @param id
     * @return Optional<Product> of Optional.empty()
     * @throws Exception
     */
    @Override
    public Optional<Product> findById(int id) throws SQLException, IllegalAccessException {
        Optional<Product> optionalProduct;
        Product product;
        String query = generateQuery(new Product(id, "", "", -1), "selectById");
        try {
            product = (Product) jdbcContext.executeSql(query, (Action) resultSet1 -> {
                ArrayList<Product> products1 = new ArrayList<>();
                if(resultSet1.next()) {
                    products1.add(resultSetToProduct(resultSet1));
                }
                return products1;
            }).get(0);

            return Optional.of(product);
        } catch (IndexOutOfBoundsException e){
            return Optional.empty();
        }

    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean create(Product product) throws SQLException, IllegalAccessException {
        return cud(product, "create");
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean update(Product product) throws SQLException, IllegalAccessException {
        return cud(product, "update");
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean delete(Product product) throws SQLException, IllegalAccessException {
        return cud(product, "delete");
    }

    private Product resultSetToProduct(ResultSet resultSet) throws SQLException, IllegalAccessException {
        return new Product(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getInt("price")
        );
    }

    private boolean cud(Product product, String option) throws IllegalAccessException, SQLException {
        String query = generateQuery(product, option);

        try{
            return jdbcContext.executeSql(query);
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    private String generateQuery(Product product, String option) throws IllegalAccessException {
        String query = null;

        if(option.equals("create")){
            query = String.format("INSERT INTO products (%s) VALUES (%s)",
                    product.getAttributesToString(),
                    product.getAttributesValueToString());
        }else if(option.equals("update")){
            String[] names = product.getAttributesToString().split(",");
            String[] values = product.getAttributesValueToString().split(",");
            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append("UPDATE products SET ");
            for (int i = 0; i < names.length; i++) {
                stringBuilder.append(names[i] + " = " + values[i]);
                if(i != names.length - 1){
                    stringBuilder.append(", ");
                }else{
                    stringBuilder.append(" ");
                }
            }
            stringBuilder.append("WHERE id = " + product.getId());
            query = stringBuilder.toString();
        }else if(option.equals("delete")){
            query = String.format("DELETE FROM products WHERE id = %d", product.getId());
        }else if(option.equals("selectAll")){
            query = "SELECT * FROM products";
        }else if(option.equals("selectById")){
            query = String.format("SELECT * FROM products WHERE id = %d", product.getId());
        }

        return query;
    }
}
