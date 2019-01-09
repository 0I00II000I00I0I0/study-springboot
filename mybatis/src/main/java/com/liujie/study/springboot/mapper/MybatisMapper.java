package com.liujie.study.springboot.mapper;

import com.liujie.study.springboot.entity.Mybatis;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MybatisMapper {

    Mybatis findById(long id);

    Mybatis findByName(String name);

    List<Mybatis> findAll();

    boolean insert(Mybatis mybatis);

    int update(Mybatis mybatis);

    int deleteById(long id);

}
