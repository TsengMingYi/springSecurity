package com.example.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDao userDao;

//    @Autowired
//    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

//    @Autowired
//    public AuthServiceImpl(
//            AuthenticationManager authenticationManager,
//            UserDetailsService userDetailsService,
//            JwtTokenUtil jwtTokenUtil) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//        this.jwtTokenUtil = jwtTokenUtil;
//    }

    @Override
    public Integer register(UserRegisterRequest userRegisterRequest) {
        User user = userDao.getUserByEmail(userRegisterRequest.getEmail());

        if(user != null){
//            log.warn("該email {} 已經被註冊",userRegisterRequest.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        //使用MD5 生成密碼的雜湊值
//        String hashedPassword = DigestUtils.md5DigestAsHex(userRegisterRequest.getPassword().getBytes());
//        userRegisterRequest.setPassword(hashedPassword);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String test = passwordEncoder.encode(userRegisterRequest.getPassword());
        userRegisterRequest.setPassword(test);

        return userDao.createUser(userRegisterRequest);
//        final String username = userToAdd.getEmail();
//        String sql = "select * from user where email = :email";
//        String sql1 = "insert into user(email,password,roles,created_date,last_modified_date) values (:email,:password,:roles,:createdDate,:lastModifiedDate)";
//        String sql2 = "select * from user where user_id = :userId";
//        Map<String,Object> map = new HashMap<>();
//        Map<String,Object> map1 = new HashMap<>();
//        Map<String,Object> map2 = new HashMap<>();
//        map.put("email",userToAdd.getEmail());
//
//        List<User> userList = namedParameterJdbcTemplate.query(sql,map,new UserRowMapper());
//        if(userList.get(0) !=null) {
//            return null;
//        }
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        final String rawPassword = userToAdd.getPassword();
////        userToAdd.setPassword(encoder.encode(rawPassword));
////        userToAdd.setLastModifiedDate(new Date());
////        userToAdd.setRoles(List.of("ROLE_USER"));
//        map1.put("email",username);
//        map1.put("password",encoder.encode(rawPassword));
//        map1.put("roles",List.of("ROLE_USER"));
//        map1.put("createdDate",new Date());
//        map1.put("lastModifiedDate",new Date());
//        KeyHolder keyHolder = new GeneratedKeyHolder();
//
//        namedParameterJdbcTemplate.update(sql1, new MapSqlParameterSource(map1),keyHolder);
//
//        int userId = keyHolder.getKey().intValue();
//        map2.put("userId",userId);
//        List<User> userList1 = namedParameterJdbcTemplate.query(sql2,map2,new UserRowMapper());
//        return userList1.get(0);
    }

    @Override
    public String login(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = (JwtUser)userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastModifiedDate())){
            return jwtTokenUtil.refreshToken(token);
        }
        return null;
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }
}
