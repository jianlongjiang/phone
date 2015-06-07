package com.phone.cn.bean.sys;

import com.phone.cn.bean.SearchBean;

@SuppressWarnings("serial")
public class SysSequenceBean extends SearchBean {
    private String seqCode;

    private String seqName;

    private String seqValue;

    public String getSeqCode() {
        return seqCode;
    }

    public void setSeqCode(String seqCode) {
        this.seqCode = seqCode == null ? null : seqCode.trim();
    }

    public String getSeqName() {
        return seqName;
    }

    public void setSeqName(String seqName) {
        this.seqName = seqName == null ? null : seqName.trim();
    }

    public String getSeqValue() {
        return seqValue;
    }

    public void setSeqValue(String seqValue) {
        this.seqValue = seqValue == null ? null : seqValue.trim();
    }

}