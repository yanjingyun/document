package com.yjy.responsibilityChain3;

public class LoginUser {
    private String loginName;
    private String password;
    private String roleName;
    private String permission;

    public LoginUser(String loginName, String password, String roleName, String permission) {
        this.loginName = loginName;
        this.password = password;
        this.roleName = roleName;
        this.permission = permission;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
