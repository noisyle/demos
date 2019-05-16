package com.noisyle.demo.optional.repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

import com.noisyle.demo.optional.bean.Role;
import com.noisyle.demo.optional.bean.User;

@Repository
public class MockUserRepository implements InitializingBean {
    private List<User> mock_users = new LinkedList<>();

    public Optional<User> findById(String id) {
        return mock_users.stream().filter(u -> u.getId().equals(id)).findFirst();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        User user = new User();
        user.setId("1");
        user.setUsername("user1");
        user.setPassword("password");

        Role role = new Role();
        role.setId("1");
        role.setRolename("ADMIN");

        user.setRole(Optional.of(role));
        mock_users.add(user);
    }
}
