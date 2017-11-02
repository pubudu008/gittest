package com.hrandika.spring.care.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "login")
public class Login extends BaseModelObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2756403908231281696L;
	@NotNull

	@Pattern(regexp = "^[a-z0-9]*$")
	@Size(min = 1, max = 50)
	@Column(name = "user_name", length = 50, unique = true, nullable = false)
	private String userName;

	@NotNull
	@Size(min = 5, max = 60)
	@Column(name = "password", length = 60)
	private String password;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private Boolean activated;

	@JsonIgnore
	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	private String activationKey;

	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	private String resetKey;

	@Column(name = "reset_date", nullable = true)
	@DateTimeFormat(iso = ISO.TIME)
	@Temporal(TemporalType.TIMESTAMP)
	private Date resetDate;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(name = "login_roles", joinColumns = {
			@JoinColumn(name = "login_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = true) })
	private Set<Role> roles = new HashSet<>();

	public Login(String userName, String password, String email, Boolean activated, Set<Role> roles) {
		super();
		this.userName = userName;
		this.password = password;
		this.email = email;
		this.activated = activated;
		this.roles = roles;
	}

}