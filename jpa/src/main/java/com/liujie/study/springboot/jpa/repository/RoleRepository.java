package com.liujie.study.springboot.jpa.repository;

import com.liujie.study.springboot.jpa.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Short> {
}
