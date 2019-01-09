package com.liujie.study.springboot.jpa.controller;

import com.liujie.study.springboot.jpa.core.BaseController;
import com.liujie.study.springboot.jpa.core.Response;
import com.liujie.study.springboot.jpa.core.RestResponse;
import com.liujie.study.springboot.jpa.core.RestResult;
import com.liujie.study.springboot.jpa.dto.form.UserAddForm;
import com.liujie.study.springboot.jpa.dto.form.UserEditForm;
import com.liujie.study.springboot.jpa.dto.form.UserSearchForm;
import com.liujie.study.springboot.jpa.entity.User;
import com.liujie.study.springboot.jpa.repository.UserCustomRepository;
import com.liujie.study.springboot.jpa.repository.UserRepository;
import com.liujie.study.springboot.jpa.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCustomRepository userCustomRepository;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object add(@Valid UserAddForm userAdd, BindingResult result) {
        if (result.hasErrors()) {
            return Response.error(this.resultErrors(result));
        }

        try {
            User user = userService.add(userAdd);
            return Response.data(user);
        } catch (Exception e) {
            return Response.error(e.getLocalizedMessage());
        }
    }


    @RequestMapping(value = "", method = RequestMethod.GET)
    public Object page(@RequestParam(name = "page", required = false) Integer page,
                       @RequestParam(name = "size", required = false) Integer size) {
        page = page == null ? 0 : Math.max(0, page);
        size = size == null ? 10 : Math.max(10, size);

        Sort sort = new Sort(Sort.Direction.DESC, "id");
        Pageable pageable = new PageRequest(page, size, sort);

        Page<User> users = userRepository.findAll(pageable);

        return Response.data(users);
    }


    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public Object search(@PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
                         UserSearchForm userSearchForm) {
        logger.info(userSearchForm.toString());
        Page<User> users = userCustomRepository.search(userSearchForm, pageable);

        return Response.data(users);
    }


    @ApiOperation(value = "根据用户ID返回数据")
    @ApiImplicitParam(name = "userId", collectionFormat = "用户ID", required = true, dataType = "Long")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public RestResult<User> get(@PathVariable Long userId) {
        if (userId > 0) {

            Optional<User> user = userRepository.findById(userId);

            logger.info(user.get().toString());
            return RestResponse.data(user.orElse(null));
        }

        return RestResponse.fail("参数错误");
    }


    @RequestMapping(value = "/username", method = RequestMethod.GET)
    public Object getUsername(@RequestParam String email) {

        if (!StringUtils.isEmpty(email)) {
            User user = userRepository.findByUsername(email);
            return Response.data(user);
        }

        return Response.error("参数错误");

    }

    @RequestMapping(value = "/{userId}/state/{state}", method = RequestMethod.PUT)
    public Object updateState(@PathVariable Long userId,
                              @PathVariable(value = "state", required = true) Integer state) {
        if (userRepository.saveState(userId, state) > 0) {
            return Response.success("操作成功");
        }
        return Response.success("操作失败");
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.PUT)
    public Object edit(@PathVariable Long userId,
                       @Valid UserEditForm userEdit, BindingResult result) {
        if (result.hasErrors()) {
            return Response.error(this.resultErrors(result));
        }

        if (userId > 0) {

            try {
                User edit = userService.edit(userId, userEdit);
                return Response.data(edit);
            } catch (Exception e) {
                return Response.error(e.getLocalizedMessage());
            }
        }
        return Response.error("参数错误");
    }


    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public Object delete(@PathVariable Long userId) {
        if (userId > 0) {

            boolean exists = userRepository.existsById(userId);

            if (exists) {
                userRepository.deleteById(userId);
                return Response.success("操作成功");
            } else {
                return Response.error("用户不存在");
            }
        }
        return Response.error("参数错误");
    }


    @RequestMapping(value = "/orders/{userId}", method = RequestMethod.GET)
    public Object orders(@PathVariable Long userId) {
        if (userId > 0) {
            Optional<User> user = userRepository.findById(userId);

            return Response.data(user.orElse(null));
        }

        return Response.error("参数错误");
    }


}
