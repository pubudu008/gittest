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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.hrandika.polymer.model.BaseModelObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * Sub Section of a large module.
 * 
 * @author Randika Hapugoda (hrandika@hotmail.com)
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "name", "module_id" }) )
public class SubModule extends BaseModelObject {

	/**
	 * *
	 */
	private static final long serialVersionUID = -3711868697956004790L;

	@NotNull
	@Size(min = 0, max = 50)
	@Column(length = 50)
	private String name;

	
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private Module module;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = {
			@JoinColumn(name = "sub_module_id", referencedColumnName = "id", nullable = true) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", referencedColumnName = "id", nullable = true) })
	private Set<Role> roles = new HashSet<>();
	
	private Boolean visible;

}// End class Module