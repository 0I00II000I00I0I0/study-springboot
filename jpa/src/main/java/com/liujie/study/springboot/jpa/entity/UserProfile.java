package com.liujie.study.springboot.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class UserProfile {

    @Id
    @JsonIgnore
    private Long userId;

    @Column(nullable = true)
    private String job;
}
