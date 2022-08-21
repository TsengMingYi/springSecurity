package com.example.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public User getUserByEmail(String email) {
        String sql = "select user_id,email,password,roles,created_date,last_modified_date from user where email = :email";
        Map<String,Object> map = new HashMap<>();
        map.put("email",email);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size() > 0){
            return userList.get(0);
        }else{
            return null;
        }
    }

    public Integer createUser(UserRegisterRequest userRegisterRequest) {
        String sql = "insert into user(email,password,roles,created_date,last_modified_date)"+
                "values (:email,:password,:roles,:createdDate,:lastModifiedDate)";
        Map<String,Object> map = new HashMap<>();
        map.put("email",userRegisterRequest.getEmail());
        map.put("password",userRegisterRequest.getPassword());
        map.put("roles",List.of("ROLE_USER"));
        Date now = new Date();
        map.put("createdDate",now);
        map.put("lastModifiedDate",now);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        int userId = keyHolder.getKey().intValue();
        return userId;
    }

    public User getUserById(Integer userId) {
        String sql = "select user_id,email,password,roles,created_date,last_modified_date from user where user_id = :userId";
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
        if(userList.size() > 0){
            return userList.get(0);
        }else{
            return null;
        }
    }
}
