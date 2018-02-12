package com.java.springboot.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author ${Suresh M Kumar}
 *
 * Dec 7, 2017
 */
@Entity
@Table(name="ROLE_TEMP")
@EntityListeners(AuditingEntityListener.class)
public class Role {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Long id;
	private String name;
	@LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate = new Date();
	@CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate = new Date();
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastModifiedDate() {
		return lastModifiedDate;
	}

	public void setLastModifiedDate(Date lastModifiedDate) {
		this.lastModifiedDate = lastModifiedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
}
