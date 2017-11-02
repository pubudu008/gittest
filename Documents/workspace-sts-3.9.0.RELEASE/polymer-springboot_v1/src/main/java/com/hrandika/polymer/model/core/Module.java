package com.hrandika.polymer.model.core;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.hrandika.polymer.model.BaseModelObject;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Module extends BaseModelObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6491660673302294950L;

	@NotNull
	@Size(min = 0, max = 50)
	@Column(length = 50, unique = true)
	private String name;

	
}// End class Application
