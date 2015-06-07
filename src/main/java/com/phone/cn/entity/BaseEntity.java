/**
 * @author zgdcool
 * @version 2015年1月21日 下午3:51:13
 *   
 */

package com.phone.cn.entity;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

import com.phone.cn.bean.SearchBean;
import com.phone.cn.utils.JsonMapper;

/**
 * ClassName:BaseEntity
 * 
 * @Function: 实体父类.
 * @Date: 2014-3-18 下午3:42:31
 * @author zengxiangzhu
 * @version
 * @since JDK 1.6
 * @see
 */
public abstract class BaseEntity<ID extends Serializable> extends SearchBean implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String createUser;
    
    
    /**
     * 更新时间
     */
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    
    /**
     * 更新者
     */
    private String updateUser;

    public abstract ID  getId() ;

    public void setId(ID id) {
        
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    @JsonIgnore
    public String getJsonObject() {
        return JsonMapper.toJsonStr(this);
    }
    
    @Override
    public String toString() {
    		return  ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
