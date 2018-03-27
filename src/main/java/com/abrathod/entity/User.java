package com.abrathod.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name="USER")
@XmlRootElement
@NamedQueries({
	@NamedQuery(name="User.findByUserName" ,query="select l from User l where l.userName= :userName1")
	})
public class User implements Serializable{

	private static final long serialVersionUID =1L ;
	
			@Id
			@GeneratedValue(strategy =GenerationType.AUTO)
			@Column(name="USER_ID")
			@JsonView(DataTablesOutput.View.class)
			private long userId;
			
			@Column(name="PASSWORD")
			@JsonView(DataTablesOutput.View.class)
			@SafeHtml
			private String password;
			
			
			@Column(name="EMAIL")
			@JsonView(DataTablesOutput.View.class)
			@SafeHtml
			private String email;
			
			@Column(name="USER_NAME")
			@JsonView(DataTablesOutput.View.class)
			@SafeHtml
			private String userName;
			
			

			public User() {
				
			}



			public long getUserId() {
				return userId;
			}



			public void setUserId(long userId) {
				this.userId = userId;
			}



			public String getPassword() {
				return password;
			}



			public void setPassword(String password) {
				this.password = password;
			}



			public String getEmail() {
				return email;
			}



			public void setEmail(String email) {
				this.email = email;
			}



			public String getUserName() {
				return userName;
			}



			public void setUserName(String userName) {
				this.userName = userName;
			}



			public User(long userId, String password, String email, String userName) {
				super();
				this.userId = userId;
				this.password = password;
				this.email = email;
				this.userName = userName;
			}



			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + (int) (userId ^ (userId >>> 32));
				result = prime * result + ((userName == null) ? 0 : userName.hashCode());
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
				User other = (User) obj;
				if (userId != other.userId)
					return false;
				if (userName == null) {
					if (other.userName != null)
						return false;
				} else if (!userName.equals(other.userName))
					return false;
				return true;
			}



			@Override
			public String toString() {
				return "User [userId=" + userId + ", password=" + password + ", email=" + email + ", userName="
						+ userName + "]";
			}

			

			
			
			
			
			
			
			
}
