package com.wangzhixuan.model.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.wangzhixuan.model.SysRole;
import com.wangzhixuan.model.SysUser;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wangzhixuan.commons.utils.JsonUtils;

/**
 * @description：UserVo
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
public class UserVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;

	@NotBlank
	@Length(min = 4, max = 20,message = "长度需要在4-20之间")
	private String loginName;

	private String name;

	@JsonIgnore
	private String password;
	@JsonIgnore
	private String salt; // 密码加密盐

	private Integer sex;

	private Integer age;

	private Integer userType;

	private Integer status;

	private Integer organizationId;

	private Date createTime;

	private String phone;

	private List<SysRole> rolesList;

	private String organizationName;

	private String roleIds;

	private Date createdateStart;
	private Date createdateEnd;

	private String createdateStartStr;
	private String createdateEndStr;

	private Date deadline;
	private Date deadlineStart;
	private Date deadlineEnd;
	private String deadlineStr;
	private String email;
	private Integer enterpriseId;

	/** 以太坊用户*/
	private String ethereumAccount;
	/** 以太坊密码*/
	private String ethereumPassword;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName == null ? null : loginName.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone == null ? null : phone.trim();
	}

	public List<SysRole> getRolesList() {
		return rolesList;
	}

	public void setRolesList(List<SysRole> rolesList) {
		this.rolesList = rolesList;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public Date getCreatedateStart() {
		return createdateStart;
	}

	public void setCreatedateStart(Date createdateStart) {
		this.createdateStart = createdateStart;
	}

	public Date getCreatedateEnd() {
		return createdateEnd;
	}

	public void setCreatedateEnd(Date createdateEnd) {
		this.createdateEnd = createdateEnd;
	}
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getDeadlineStart() {
		return deadlineStart;
	}

	public void setDeadlineStart(Date deadlineStart) {
		this.deadlineStart = deadlineStart;
	}

	public Date getDeadlineEnd() {
		return deadlineEnd;
	}

	public void setDeadlineEnd(Date deadlineEnd) {
		this.deadlineEnd = deadlineEnd;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getCreatedateStartStr() {
		return createdateStartStr;
	}

	public void setCreatedateStartStr(String createdateStartStr) {
		this.createdateStartStr = createdateStartStr;
	}

	public String getCreatedateEndStr() {
		return createdateEndStr;
	}

	public void setCreatedateEndStr(String createdateEndStr) {
		this.createdateEndStr = createdateEndStr;
	}

	/**
	 * 比较vo和数据库中的用户是否同一个user，采用id比较
	 * @param user 用户
	 * @return 是否同一个人
	 */
	public boolean equalsUser(SysUser user) {
		if (user == null) {
			return false;
		}
		Long userId = user.getId();
		if (id == null || userId == null) {
			return false;
		}
		return id.equals(userId);
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

	public String getDeadlineStr() {
		return deadlineStr;
	}

	public void setDeadlineStr(String deadlineStr) {
		this.deadlineStr = deadlineStr;
	}

	public String getEthereumAccount() {
		return ethereumAccount;
	}

	public void setEthereumAccount(String ethereumAccount) {
		this.ethereumAccount = ethereumAccount;
	}

	public String getEthereumPassword() {
		return ethereumPassword;
	}

	public void setEthereumPassword(String ethereumPassword) {
		this.ethereumPassword = ethereumPassword;
	}
}