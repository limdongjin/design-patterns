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
    public void getName() {
    }

    @Test
    public void getDescription() {
    }

    @Test
    public void getPrice() {
    }
}