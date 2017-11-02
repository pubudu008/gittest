package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;

import org.hibernate.envers.Audited;
import org.hibernate.envers.RelationTargetAuditMode;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.demo.listener.AuditListener;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Audited
@EntityListeners(AuditListener.class)
public class User{
	
	@Id
	@GeneratedValue
	private long userId;
	
	private String firstName;
	
	private String lastName;

	@Audited(targetAuditMode=RelationTargetAuditMode.AUDITED)
	//@JsonIgnore
	@OneToOne
	private Company company;

	
}
