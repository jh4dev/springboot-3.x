package com.jh4dev.myapp.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.jh4dev.myapp.bean.AdminUser;
import com.jh4dev.myapp.bean.AdminUserV2;
import com.jh4dev.myapp.bean.User;
import com.jh4dev.myapp.dao.UserDaoService;
import com.jh4dev.myapp.exception.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    private final UserDaoService userDaoService;

    public AdminUserController(UserDaoService userDaoService) {
        this.userDaoService = userDaoService;
    }

    @GetMapping("/users")
    public MappingJacksonValue getUserListForAdmin() {

        List<User> userList = userDaoService.findAll();

        List<AdminUser> adminUserList = new ArrayList<>();
        for(User user : userList) {
            AdminUser au = new AdminUser();
            BeanUtils.copyProperties(user, au);
            adminUserList.add(au);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUserList);
        mapping.setFilters(filters);

        return mapping;

    }

    /*
     * [버전관리]
     * 1. URI 사용
     *      > X (구 트위터) 에서 주로 사용
     *      > @GetMapping("/v1/users/{id}")
     *
     * 2. Parameter 사용
     *      > Amazon 주로 사용
     *      > @GetMapping(value = "/users/{id}", params = "version=1")
     *
     * 3. Http Headers Key-Value 사용
     *      > MS 주로 사용
     *      > @GetMapping(value = "/users/{id}", headers = "x-api-version=1")
     *
     * 4. Http Headers Mime 사용
     *      > Github 주로 사용
     *      > @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
     * */
    @GetMapping("/v1/users/{id}")
    public MappingJacksonValue getUserForAdminURI(@PathVariable int id) {

        User user = userDaoService.findOne(id);
        AdminUser adminUser = new AdminUser();

        if(user == null) {
            throw new UserNotFoundException(String.format("User [%s] not found", id));

        } else {
            BeanUtils.copyProperties(user, adminUser);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "ssn");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

    @GetMapping("/v2/users/{id}")
//    @GetMapping(value = "/users/{id}", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "x-api-version=2")
//    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public MappingJacksonValue getUserForAdminURIV2(@PathVariable int id) {

        User user = userDaoService.findOne(id);
        AdminUserV2 adminUser = new AdminUserV2();

        if(user == null) {
            throw new UserNotFoundException(String.format("User [%s] not found", id));

        } else {
            BeanUtils.copyProperties(user, adminUser);
            adminUser.setGrade("VIP");
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id", "name", "joinDate", "grade");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(adminUser);
        mapping.setFilters(filters);

        return mapping;
    }

}
