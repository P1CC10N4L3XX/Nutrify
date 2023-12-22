package com.dicii.ispw.project.beans;

public class CredentialsBean {

    private String email;
    private String password;

    private CredentialsBean() {}

    public static CredentialsBean ctorWithSyntaxCheck(String email, String password) {
        /*This is a constructor with syntax check and is used by view*/
        CredentialsBean credentialsBean = new CredentialsBean();
        //credentialsBean.setEmail(email);
        //credentialsBean.setPassword(password);
        return credentialsBean;
    }


}
