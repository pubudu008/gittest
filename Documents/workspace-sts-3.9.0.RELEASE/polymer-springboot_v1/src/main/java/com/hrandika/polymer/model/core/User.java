package com.hrandika.polymer.model.core;

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
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hrandika.polymer.model.BaseModelObject;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User extends BaseModelObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8416541109572682660L;

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

	@Size(max = 20)
	@Column(name = "activation_key", length = 20)
	@JsonIgnore
	private String activationKey;

	@Size(max = 20)
	@Column(name = "reset_key", length = 20)
	private String resetKey;

	@Column(name = "reset_date", nullable = true)
	@DateTimeFormat(iso = ISO.TIME)
	@Temporal(TemporalType.TIMESTAMP)
	private Date resetDate;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = true) })
	private Set<Role> roles = new HashSet<>();

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "module_id", referencedColumnName = "id", nullable = true) })
	private Set<Module> modules = new HashSet<>();

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = {
			@JoinColumn(name = "user_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "sub_module_id", referencedColumnName = "id", nullable = true) })
	private Set<SubModule> subModules = new HashSet<>();

	@ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	private Company company;

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}// End set Password

}// End class User
