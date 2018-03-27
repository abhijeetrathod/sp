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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.abrathod.common.PrePersistUtil;
import com.fasterxml.jackson.annotation.JsonView;


/**
 *
 * @author abhijeetR
 */
@Entity
@Table(name = "ROLES")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Roles.findAll", query = "SELECT l FROM LtP2pRoles l"),
		
		@NamedQuery(name = "Roles.findByRoleId", query = "SELECT l FROM LtP2pRoles l WHERE l.roleId = :roleId"),

		})
public class Roles implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	
	@JsonView(DataTablesOutput.View.class)
	@Column(name = "ROLE_ID")
	private Long roleId;
	
	@Basic(optional = false)
	@NotNull
	
	@JsonView(DataTablesOutput.View.class)
	@SafeHtml
	@Column(name = "ROLE_NAME")
	private String roleName;
	

	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String makerName;

	@Transient
	@JsonView(DataTablesOutput.View.class)
	private String checkerName;

	public Roles() {
	}

	public Roles(Long roleId) {
		this.roleId = roleId;
	}

	@PrePersist
	void preInsert() {
		PrePersistUtil.pre(this);
	}

	public Roles(Long roleId, String roleName, Date startDate, long createdBy, Date creationDate,
			long lastUpdateLogin) {
		this.roleId = roleId;
		this.roleName = roleName;
		
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {

		this.roleName = roleName;
	}

	

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
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
		Roles other = (Roles) obj;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LtP2pRoles [roleId=" + roleId + ", roleName=" + roleName + ", roleDesc=" + "]";
	}

}
