package com.phone.cn.entity.sys;

import com.phone.cn.entity.BaseEntity;

@SuppressWarnings("serial")
public class RedPacketRule extends BaseEntity<Integer>{
    private Integer id;

    private Integer ruleId;

    private Integer start;

    private Integer end;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRuleId() {
        return ruleId;
    }

    public void setRuleId(Integer ruleId) {
        this.ruleId = ruleId;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }
}