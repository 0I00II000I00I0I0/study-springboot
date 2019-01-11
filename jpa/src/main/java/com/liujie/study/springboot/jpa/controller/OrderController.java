package com.liujie.study.springboot.jpa.controller;

import com.liujie.study.springboot.jpa.core.BaseController;
import com.liujie.study.springboot.jpa.core.Response;
import com.liujie.study.springboot.jpa.entity.Order;
import com.liujie.study.springboot.jpa.entity.Product;
import com.liujie.study.springboot.jpa.entity.User;
import com.liujie.study.springboot.jpa.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderRepository orderRepository;

    @RequestMapping(value = "/{orderId}", method = RequestMethod.GET)
    public Object get(@PathVariable Long orderId) {
        if (orderId > 0) {
            Optional<Order> order = orderRepository.findById(orderId);
            return Response.data(order.orElse(null));
        }

        return Response.error("参数错误");
    }

    @RequestMapping(value = "/product/{orderId}", method = RequestMethod.GET)
    public Object Product(@PathVariable Long orderId) {
        if (orderId > 0) {
            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                List<Product> products = order.get().getProducts();
                return Response.data(products);
            }
        }
        return Response.error("参数错误");
    }

    @RequestMapping(value = "/user/{orderId}", method = RequestMethod.GET)
    public Object user(@PathVariable Long orderId) {
        if (orderId >0) {
            Optional<Order> order = orderRepository.findById(orderId);
            if (order.isPresent()) {
                User user = (User) order.get().getUser();
                return Response.data(user);
            }
        }
        return Response.error("参数错误");
    }


}
