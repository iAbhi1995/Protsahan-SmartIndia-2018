package com.mota.tribal.protsahan.Login.Model.Data;

public class UserInfo {
    private String message, token, username, id;
    private boolean success;

    public UserInfo(boolean success, String message, String token, String username, String id) {
        this.success = success;
        this.message = message;
        this.token = token;
        this.username = username;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

