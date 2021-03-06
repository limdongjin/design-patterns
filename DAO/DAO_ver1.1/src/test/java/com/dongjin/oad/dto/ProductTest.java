package com.dongjin.oad.dto;

import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void getId() {
        Product product = new Product(1, "제품1", "설명!!", 10000);
        assertEquals(product.getId(), 1);
    }

    @Test
    public void equals(){
        Product product = new Product(4534, "fnlkn", "fdaaa", 111);
        Product product2 = new Product(4534, "fnlkn", "fdaaa", 111);
        assertEquals(product, product2);
        assertNotSame(product, product2);
    }

    @Test
    public void getAttributesToString(){
        Product product = new Product(1, "제품1", "설명!!", 10000);
        System.out.println(product.getAttributesToString());
    }

    @Test
    public void getAttributesValueToString() throws IllegalAccessException{
        Product product = new Product(1, "제품1", "설명!!", 10000);
        System.out.println(product.getAttributesValueToString());
        assertEquals(product.getAttributesValueToString(), "1,\"제품1\",\"설명!!\",10000");
    }

    @Test
    public void getAttributes() throws IllegalAccessException {
        Product product = new Product(1, "제품1", "설명!!", 10000);
        System.out.println(product.getAttributes());
        System.out.println(product.getAttributes().keySet());
        product.getAttributes().keySet()
                .forEach(key -> {
                    System.out.println(key);
                    try {
                        System.out.println(product.getAttributes().get(key));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                });
    }

}