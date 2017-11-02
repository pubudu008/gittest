package com.hrandika.polymer.model;
	
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author Gunnar Hillert
 * @author Randika Hapugoda
 * @since 1.0
 *
 */
@MappedSuperclass
@Getter
@Setter
public abstract class BaseModelObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4194525198831057382L;

	
	@Id		
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	protected Long id;

	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;
	
	@JsonIgnore
	@Temporal(TemporalType.TIMESTAMP)
	protected Date updatedDate;

	@JsonIgnore
	@Version
	protected Integer version;

	@PrePersist
	protected void onCreate() {
		this.createdDate = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedDate = new Date();
	}

}// End Class BaseModelObject
