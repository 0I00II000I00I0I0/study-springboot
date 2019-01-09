package com.liujie.study.springboot.jpa.repository;

import com.liujie.study.springboot.jpa.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

}
