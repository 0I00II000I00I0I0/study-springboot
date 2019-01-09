package com.liujie.study.springboot.jpa.repository;

import com.liujie.study.springboot.jpa.dto.form.UserSearchForm;
import com.liujie.study.springboot.jpa.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCustomRepository {

    Page<User> search(UserSearchForm userSearchForm, Pageable pageRequest);

}
