package com.phone.cn.entity.member;

import com.phone.cn.entity.BaseEntity;
/**
 * 注册表
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class Registration extends BaseEntity<Integer> {
    private Integer id;

    private Integer user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}