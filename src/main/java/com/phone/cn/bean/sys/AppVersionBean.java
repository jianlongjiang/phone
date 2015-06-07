package com.phone.cn.bean.sys;

import com.phone.cn.bean.SearchBean;

@SuppressWarnings("serial")
public class AppVersionBean extends SearchBean {
    private Integer id;

    private String version;

    private String minVersion;

    private String updateStatus;

    private String updateMsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getMinVersion() {
        return minVersion;
    }

    public void setMinVersion(String minVersion) {
        this.minVersion = minVersion == null ? null : minVersion.trim();
    }

    public String getUpdateStatus() {
        return updateStatus;
    }

    public void setUpdateStatus(String updateStatus) {
        this.updateStatus = updateStatus == null ? null : updateStatus.trim();
    }

    public String getUpdateMsg() {
        return updateMsg;
    }

    public void setUpdateMsg(String updateMsg) {
        this.updateMsg = updateMsg == null ? null : updateMsg.trim();
    }
}