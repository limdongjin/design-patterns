package com.dongjin.oad.dao;

import com.dongjin.oad.dto.Product;
import com.dongjin.oad.util.JdbcContext;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

public class ProductDaoImpl implements ProductDao {

    private final JdbcContext jdbcContext;


    public ProductDaoImpl(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    /**
     * @return all products as a stream.
     * @throws Exception
     */
    @Override
    public Stream findAll() throws  SQLException, IllegalAccessException {
          ArrayList products;
        String query = generateQuery(null, "selectAll");

        products = jdbcContext.executeSql(query, resultSet1 -> {
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
        Product product;
        String query = generateQuery(new Product(id, "", "", -1), "selectById");
        try {
            product = (Product) jdbcContext.executeSql(query, resultSet1 -> {
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
    public boolean create(@NotNull Product product) throws SQLException, IllegalAccessException {
        return cud(product, "create");
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean update(@NotNull Product product) throws SQLException, IllegalAccessException {
        return cud(product, "update");
    }

    /**
     * @param product instance
     * @return true or false
     * @throws Exception
     */
    @Override
    public boolean delete(@NotNull Product product) throws SQLException, IllegalAccessException {
        return cud(product, "delete");
    }

    private Product resultSetToProduct(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getInt("price")
        );
    }

    private boolean cud(@NotNull Product product, @NotNull String option) throws IllegalAccessException, SQLException {
        String query = generateQuery(product, option);

        try{
            return jdbcContext.executeSql(query);
        }catch (SQLException e){
            e.printStackTrace();
            throw e;
        }
    }

    @Nullable
    private String generateQuery(@NotNull Product product, String option) throws IllegalAccessException {
        String query = null;

        switch (option) {
            case "create":
                query = String.format("INSERT INTO products (%s) VALUES (%s)",
                        product.getAttributesToString(),
                        product.getAttributesValueToString());
                break;
            case "update":
                String[] names = product.getAttributesToString().split(",");
                String[] values = product.getAttributesValueToString().split(",");
                StringBuilder stringBuilder = new StringBuilder();

                stringBuilder.append("UPDATE products SET ");
                for (int i = 0; i < names.length; i++) {
                    stringBuilder.append(names[i]).append(" = ").append(values[i]);
                    if (i != names.length - 1) {
                        stringBuilder.append(", ");
                    } else {
                        stringBuilder.append(" ");
                    }
                }
                stringBuilder.append("WHERE id = ").append(product.getId());
                query = stringBuilder.toString();
                break;
            case "delete":
                query = String.format("DELETE FROM products WHERE id = %d", product.getId());
                break;
            case "selectAll":
                query = "SELECT * FROM products";
                break;
            case "selectById":
                query = String.format("SELECT * FROM products WHERE id = %d", product.getId());
                break;
        }

        return query;
    }
}
