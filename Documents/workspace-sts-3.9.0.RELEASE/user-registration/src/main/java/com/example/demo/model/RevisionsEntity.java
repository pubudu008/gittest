package com.example.demo.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

@Entity
@RevisionEntity
public class RevisionsEntity {

	@Id
	@GeneratedValue
	@RevisionNumber
private Long revisionId;

	@RevisionTimestamp
	private Date revisionDate;
}
