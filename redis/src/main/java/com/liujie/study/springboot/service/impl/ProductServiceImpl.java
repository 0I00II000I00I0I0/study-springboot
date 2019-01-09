package com.liujie.study.springboot.service.impl;

import com.liujie.study.springboot.entity.Product;
import com.liujie.study.springboot.mapper.ProductMapper;
import com.liujie.study.springboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Condition;

import java.util.List;

@Service
@CacheConfig(cacheNames = "product")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Override
    @CachePut(key = "#product.id")
    public Product save(Product product) {
        int save = productMapper.updateByPrimaryKey(product);
        return product;
    }

    @Override
    @Cacheable
    public Product getProductById(Long productId) {
        Product product = productMapper.selectByPrimaryKey(productId);
        return product;
    }

    @Override
    @Cacheable(key = "'greater_' + #id")
    public List<Product> getProductWhere(Integer id) {
        Condition condition = new Condition(Product.class);
        condition.createCriteria();

        condition.and().andGreaterThan("id", id);
        condition.orderBy("id").desc();

        List<Product> products = productMapper.selectByExample(condition);
        return products;
    }

    @Override
    @Cacheable
    public List<Product> getProductAll() {
        List<Product> products = productMapper.selectAll();

        return products;
    }
}
