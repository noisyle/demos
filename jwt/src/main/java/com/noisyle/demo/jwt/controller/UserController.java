package com.noisyle.demo.jwt.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.noisyle.demo.jwt.repository.UserRepository;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public Object users() {
        return ResponseEntity.ok(userRepository.getUsers());
    }

    @GetMapping("/user/{username}")
    public Object user(@PathVariable("username") String username) {
        return ResponseEntity.ok(userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username " + username + "not found")));
    }

    @GetMapping("/me")
    public ResponseEntity<Map<Object, Object>> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
        Map<Object, Object> model = new HashMap<>();
        model.put("username", userDetails.getUsername());
        model.put("roles", userDetails.getAuthorities().stream().map(a -> ((GrantedAuthority) a).getAuthority())
                .collect(Collectors.toList()));
        return ResponseEntity.ok(model);
    }
}
