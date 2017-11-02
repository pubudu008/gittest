package com.hrandika.polymer.model.core;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import com.hrandika.polymer.model.BaseModelObject;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Company extends BaseModelObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6461534932428125776L;

	@Size(min = 1, max = 50)
	private String name;

	@Email
	@Size(max = 100)
	@Column(length = 100, unique = true)
	private String email;

	@OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<User>();

	@ManyToMany(cascade = CascadeType.DETACH)
	@JoinTable(joinColumns = { @JoinColumn(name = "company_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "module_id", referencedColumnName = "id") })
	private Set<Module> modules = new HashSet<Module>();

}// End class Company
