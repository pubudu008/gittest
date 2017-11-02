package com.example.demo.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.listener.AuditListener;
import com.example.demo.serializable.ComapnyDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
@Audited
@EntityListeners(AuditListener.class)
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
@JsonSerialize( using = ComapnyDeserialize.class )
public class Company implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1478398278167128622L;
	@Id
	@GeneratedValue
	private long id;
	private String name;

	private String email;
	@Audited(targetAuditMode=RelationTargetAuditMode.NOT_AUDITED)
@OneToMany(mappedBy="company")
private Set<Building> building;
	
}
