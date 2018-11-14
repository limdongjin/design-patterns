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
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/dongjin?autoReconnect=true&useSSL=false&characterEncoding=UTF-8&serverTimezone=UTC";
        String id = "root";
        String pw = "password1";
        ProductDao productDao;
        try {
            productDao = new ProductDaoImpl(new JdbcContext(new DataSourceImplOnlyConnection(url, id, pw)));
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }

        try {
            productDao.findAll().forEach(product -> System.out.println(product));
            System.out.println("\n\n\n");
            Product product = new Product(11, "hello world", "desc", 12113);

            productDao.create(product);
            System.out.println(productDao.findById(11));
            System.out.println("\n\n\n");

            productDao.delete(product);
            System.out.println(productDao.findById(11));
            System.out.println("\n\n\n");

            productDao.create(product);
            System.out.println(productDao.findById(121));
            product.setId(121);
            productDao.create(product);
            System.out.println(productDao.findById(121));
            System.out.println("\n\n\n");

            product = productDao.findById(121).get();
            product.setName("World Hello!! !!!");
            productDao.update(product);

            System.out.println(productDao.findById(121));

            productDao.findAll().forEach(product1 -> System.out.println(product1));
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

    }
}
