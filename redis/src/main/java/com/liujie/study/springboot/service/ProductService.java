package com.liujie.study.springboot.service;

import com.liujie.study.springboot.entity.Product;

import java.util.List;

public interface ProductService {

    Product save(Product product);

    Product getProductById(Long productId);

    List<Product> getProductWhere(Integer id);

    List<Product> getProductAll();

}
