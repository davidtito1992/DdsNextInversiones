package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Proxy;
import org.uqbar.commons.utils.Observable;
import org.uqbar.commons.utils.Transactional;

@Entity
@Observable
@Table(name = "User")
@Transactional
@Proxy(lazy = false)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String email;
	private String password;

	public User(Long id, String email, String password) {
		this.userId = id;
		this.email = email;
		this.password = password;
	}

	public User() {
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

}
