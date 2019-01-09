package com.liujie.study.springboot.controller;

import com.liujie.study.springboot.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/rest")
public class RestfulController {

    private final static Logger logger = LoggerFactory.getLogger(RestfulController.class);

    private final AtomicLong counter = new AtomicLong();

    // @RequestMapping("")，如果为空，支持/rest和/rest/访问
    @RequestMapping("/")
    public String index() {
        return "hello rest";
    }

    @RequestMapping("/greeing")
    public User index(@RequestParam(value = "name", defaultValue = "world") String name) {
        User user = new User();
        user.setId(counter.incrementAndGet());
        user.setName(name);
        return user;
    }
}
