package com.liujie.study.springboot.jpa.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Role {

    @Id
    @GeneratedValue
    private Short id;

    @Column(nullable = false)
    private String name;
}
