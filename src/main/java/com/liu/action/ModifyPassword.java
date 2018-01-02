package com.liu.action;

import org.springframework.stereotype.Controller;

/**
 * Created by Crazy Ones on 2018/1/2.
 */
@Controller
public class ModifyPassword {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
