package com.dicii.ispw.project.beans;

public class UserBean {
    private String email;
    private String password;
    private Boolean type;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    public Boolean getType(){
        return type;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Boolean type) {
        this.type = type;
    }
}
