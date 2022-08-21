package com.example.json;

public interface AuthService {
    Integer register(UserRegisterRequest userRegisterRequest);
    String login(String username, String password);
    String refresh(String oldToken);
    User getUserById(Integer userId);
}
