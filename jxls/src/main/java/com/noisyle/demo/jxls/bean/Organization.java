package com.noisyle.demo.jxls.bean;

public class Organization {
    private String code;
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Organization(String code, String name) {
        super();
        this.code = code;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Organization [code=" + code + ", name=" + name + "]";
    }
}
