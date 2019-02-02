package com.noisyle.demo.jxls.bean;

import java.util.List;

public class Employee {
    private String code;
    private String name;
    private List<Education> educations;
    private List<Working> workings;
    private List<Organization> orgs;

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

    public List<Education> getEducations() {
        return educations;
    }

    public void setEducations(List<Education> educations) {
        this.educations = educations;
    }

    public List<Working> getWorkings() {
        return workings;
    }

    public void setWorkings(List<Working> workings) {
        this.workings = workings;
    }

    public List<Organization> getOrgs() {
        return orgs;
    }

    public void setOrgs(List<Organization> orgs) {
        this.orgs = orgs;
    }

    public Employee(String code, String name, List<Education> educations, List<Working> workings,
            List<Organization> orgs) {
        super();
        this.code = code;
        this.name = name;
        this.educations = educations;
        this.workings = workings;
        this.orgs = orgs;
    }

}
