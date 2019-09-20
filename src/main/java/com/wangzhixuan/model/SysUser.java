package com.wangzhixuan.model;

import com.wangzhixuan.commons.utils.JsonUtils;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * 用户
 *
 */
public class SysUser implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 主键id */
	private Long id;

	/** 登陆名 */
	private String loginName;

	/** 用户名 */
	private String name;

	/** 密码 */
	private String password;
	
	/** 密码加密盐 */
	private String salt;

	/** 性别 */
	private Integer sex;

	/** 年龄 */
	private Integer age;

	/** 手机号 */
	private String phone;

	/** 用户类别 */
	private Integer userType;

	/** 用户状态 */
	private Integer status;

	/** 所属机构 */
	private Integer organizationId;

	/** 创建时间 */
	private Date createTime;
	/**截止日期*/
	private Date deadline;
	/**邮箱*/
	private String email;
	/**企业id*/
	private Integer enterpriseId;

	/** 以太坊用户*/
	private String ethereumAccount;
	/** 以太坊密码*/
	private String ethereumPassword;


	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return this.loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public Integer getSex() {
		return this.sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getAge() {
		return this.age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getUserType() {
		return this.userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrganizationId() {
		return this.organizationId;
	}

	public void setOrganizationId(Integer organizationId) {
		this.organizationId = organizationId;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Integer enterpriseId) {
		this.enterpriseId = enterpriseId;
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
