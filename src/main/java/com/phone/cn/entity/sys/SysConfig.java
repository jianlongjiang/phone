package com.phone.cn.entity.sys;

import com.phone.cn.entity.BaseEntity;
/**
 * 积分和经验类
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class SysConfig extends BaseEntity<Integer> {
    private Integer id;

    private String configName;

    private String configValue;

    private String configDesc;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName == null ? null : configName.trim();
    }

    public String getConfigValue() {
        return configValue;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue == null ? null : configValue.trim();
    }

    public String getConfigDesc() {
        return configDesc;
    }

    public void setConfigDesc(String configDesc) {
        this.configDesc = configDesc == null ? null : configDesc.trim();
    }
}