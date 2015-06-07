package com.phone.cn.entity.sys;

import com.phone.cn.entity.BaseEntity;

import java.util.Date;
/**
 * 登录信息类
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class LoginLog extends BaseEntity<Integer> {
    private Integer id;

    private String loginIp;

    private Date loginTime;

    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp == null ? null : loginIp.trim();
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}