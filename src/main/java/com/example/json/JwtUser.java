package com.example.json;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

public class JwtUser implements UserDetails {

    private Integer userId;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
    private Date createdDate;
    private Date lastModifiedDate;

    public JwtUser(Integer userId, String email, String password, Collection<? extends GrantedAuthority> authorities, Date createdDate, Date lastModifiedDate) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    @JsonIgnore
    public Integer getUserId() {
        return userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return true;
    }

    @JsonIgnore
    public Date getCreatedDate() {
        return createdDate;
    }

    @JsonIgnore
    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }
}
