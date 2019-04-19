package com.noisyle.demo.jwt.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.noisyle.demo.jwt.domain.auth.Role;
import com.noisyle.demo.jwt.domain.auth.UserDetail;

@Component(value="CustomUserDetailsService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetail loadUserByUsername(String name) throws UsernameNotFoundException {
        System.out.println("=========="+name);
        return new UserDetail(name, name, Role.builder().name(name).build());
    }
}
