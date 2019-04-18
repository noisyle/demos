package com.example.auth.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.auth.demo.service.AuthService;
import com.noisyle.demo.jwt.domain.ResultCode;
import com.noisyle.demo.jwt.domain.ResultJson;
import com.noisyle.demo.jwt.domain.auth.ResponseUserToken;
import com.noisyle.demo.jwt.domain.auth.Role;
import com.noisyle.demo.jwt.domain.auth.User;
import com.noisyle.demo.jwt.domain.auth.UserDetail;

@RestController
@RequestMapping("/api/v1")
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/login")
    public ResultJson<ResponseUserToken> login(@Valid @RequestBody User user) {
        final ResponseUserToken response = authService.login(user.getName(), user.getPassword());
        return ResultJson.ok(response);
    }

    @GetMapping(value = "/logout")
    public ResultJson logout(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        authService.logout(token);
        return ResultJson.ok();
    }

    @GetMapping(value = "/user")
    public ResultJson getUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        if (token == null) {
            return ResultJson.failure(ResultCode.UNAUTHORIZED);
        }
        UserDetail userDetail = authService.getUserByToken(token);
        return ResultJson.ok(userDetail);
    }

    @PostMapping(value = "/sign")
    public ResultJson sign(@RequestBody User user) {
        if (StringUtils.isAnyBlank(user.getName(), user.getPassword())) {
            return ResultJson.failure(ResultCode.BAD_REQUEST);
        }
        UserDetail userDetail = new UserDetail(user.getName(), user.getPassword(), Role.builder().id(1l).build());
        return ResultJson.ok(authService.register(userDetail));
    }
}
