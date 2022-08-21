package com.example.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UserDao userDao;


    private JwtUser jwtUser;

//    @GetMapping("/users")
//    public List<User> getUsers(){
//        String sql = "select * from user";
//        Map<String,Object> map = new HashMap<>();
//        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
//        return userList;
//    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/users/{email}")
    public User getUserByUsername(@PathVariable String email) {
        return userDao.getUserByEmail(email);
    }
}
