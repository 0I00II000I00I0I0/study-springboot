package com.liujie.study.springboot.mapper;

import com.liujie.study.springboot.entity.Product;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface ProductMapper extends Mapper<Product> {
}
