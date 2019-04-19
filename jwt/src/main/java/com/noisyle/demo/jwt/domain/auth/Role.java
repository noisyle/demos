package com.noisyle.demo.jwt.domain.auth;

public class Role {
    private Long id;
    private String name;
    private String nameZh;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameZh() {
        return nameZh;
    }

    public void setNameZh(String nameZh) {
        this.nameZh = nameZh;
    }

    static public Builder builder() {
        return new Builder();
    }

    static public class Builder {
        private Role obj = new Role();
        
        public Builder id(Long id) {
            obj.id = id;
            return this;
        }

        public Builder name(String name) {
            obj.name = name;
            return this;
        }

        public Role build() {
            return obj;
        }
    }
}
