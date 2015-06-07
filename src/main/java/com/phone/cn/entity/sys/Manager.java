package com.phone.cn.entity.sys;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.phone.cn.entity.BaseEntity;

/**
 * 管理员类
 * 
 * @author zgd
 *
 */
@SuppressWarnings("serial")
public class Manager extends BaseEntity<Integer> implements UserDetails {
	private Integer id;

	private String account;

	private String password;

	private String nickname;

	private String realName;

	private String workNo;

	// private String roleId;
	//  辅助字段, 后台创建用户,指定角色使用
	private Integer roleId;
	
	
	private Map<String,Integer> toUrlMap=new HashMap<String, Integer>() ;
	  private  Set<GrantedAuthority> authorities;

	
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public Map<String, Integer> getToUrlMap() {
		return toUrlMap;
	}

	public void setToUrlMap(Map<String, Integer> toUrlMap) {
		this.toUrlMap = toUrlMap;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return  authorities;
	}

	@Override
	public String getUsername() {
		return account;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}