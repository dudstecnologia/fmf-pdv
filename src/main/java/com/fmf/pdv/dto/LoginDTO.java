package com.fmf.pdv.dto;

import com.fmf.pdv.model.User;

public class LoginDTO {
    private User user;
    private boolean success;
    
    public LoginDTO(boolean success) {
        this.success = success;
    }

    public LoginDTO(User user, boolean success) {
        this.user = user;
        this.success = success;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
