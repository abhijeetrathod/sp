/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.abrathod.entity;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrePersist;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.abrathod.common.PrePersistUtil;
import com.fasterxml.jackson.annotation.JsonView;


/**
 *
 * @author Dattatrays28
 */
@Entity
@Table(name = "USER_ROLES")
@XmlRootElement

@NamedQueries({ 
		@NamedQuery(name = "userRoles.findAllActiveRoleName", query = "SELECT role.roleName FROM UserRoles l,Roles role WHERE l.userId = :userId AND l.roleId = role.roleId"),
		@NamedQuery(name = "USER_ROLES.findAllActive", query = "SELECT l FROM UserRoles l WHERE (l.status = 'Active' OR l.status = 'ACTIVE'))"),
		})
public class UserRoles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "USER_ROLE_ID")
	private Long userRoleId;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "USER_ID")
	private Long userId;
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "STATUS")
	private String status; 
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String updateFlag;

	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String roleName;
	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String roleDesc;

	public UserRoles() {
	}

	@PrePersist
	void preInsert() {
		PrePersistUtil.pre(this);
	}

	public UserRoles(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public UserRoles(Long userRoleId, Date startDate, long createdBy, Date creationDate, long lastUpdateLogin) {
		this.userRoleId = userRoleId;
		
	}

	public Long getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(Long userRoleId) {
		this.userRoleId = userRoleId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	

	public String getUpdateFlag() {
		return updateFlag;
	}

	public void setUpdateFlag(String updateFlag) {
		this.updateFlag = updateFlag;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		if (roleDesc == null || roleDesc.equals("null"))
			roleDesc = "";
		this.roleDesc = roleDesc;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		if (roleName == null || roleName.equals("null"))
			roleName = "";
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userRoleId == null) ? 0 : userRoleId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserRoles other = (UserRoles) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userRoleId == null) {
			if (other.userRoleId != null)
				return false;
		} else if (!userRoleId.equals(other.userRoleId))
			return false;

		return true;
	}

	@Override
	public String toString() {
		return "UserRoles [userRoleId=" + userRoleId + ", userId=" + userId + ", roleId=" + roleId + ", status="
				+ status + ", updateFlag=" + updateFlag + ", roleName=" + roleName + ", roleDesc=" + roleDesc + "]";
	}

	

}
