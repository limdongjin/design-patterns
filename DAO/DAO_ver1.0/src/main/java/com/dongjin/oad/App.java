package com.dongjin.oad;

import com.dongjin.oad.dao.ProductDao;
import com.dongjin.oad.dao.ProductDaoImpl;
import com.dongjin.oad.dto.Product;
import com.dongjin.oad.util.DataSourceImplOnlyConnection;
import com.dongjin.oad.util.JdbcContext;

import javax.sql.DataSource;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.stream.Stream;

public class App {
    public static void main(String[] args) throws Exception {
        DataSourceImplOnlyConnection dataSource = new DataSourceImplOnlyConnection();
        Stream<Product> productsStream;

        String url = "jdbc:mysql://localhost:3306/dongjin?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
        String id = "root";
        String pw = "password1";

        dataSource.setConnection(url, id, pw);

        ProductDao productDao = new ProductDaoImpl(new JdbcContext(dataSource));
        productsStream = productDao.findAll();

        productsStream.forEach(product -> System.out.println(product.toString()));
    }
}
