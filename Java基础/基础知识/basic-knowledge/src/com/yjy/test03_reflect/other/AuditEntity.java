package com.yjy.test03_reflect.other;

import java.sql.Timestamp;

public class AuditEntity extends VersionEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7655450094190788933L;

	// 创建人
	private String createUser;
	// 创建时间
	private Timestamp createTime;
	// 最后更新人
	private String lastUpdateUser;
	// 最后更新时间
	private Timestamp lastUpdateTime;
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public Timestamp getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(Timestamp lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
