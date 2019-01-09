package com.liujie.study.springboot.jpa.repository;

import com.liujie.study.springboot.jpa.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
