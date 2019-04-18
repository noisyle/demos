package com.noisyle.demo.jwt.domain.auth;

public class ResponseUserToken {
    private String token;
    private UserDetail userDetail;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public ResponseUserToken(String token, UserDetail userDetail) {
        super();
        this.token = token;
        this.userDetail = userDetail;
    }

}
