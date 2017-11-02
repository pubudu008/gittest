package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class Building {

	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String code;

	@Audited(targetAuditMode=RelationTargetAuditMode.AUDITED)
	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;

	


}
