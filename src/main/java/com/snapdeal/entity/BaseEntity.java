package com.snapdeal.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.security.core.context.SecurityContextHolder;

@MappedSuperclass
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created", nullable = false)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated", nullable = false,updatable=true)
	private Date updated;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User createdBy;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User updatedBy;
	
	public User getCreatedBy() {
		return createdBy;
	}

	public User getUpdatedBy() {
		return updatedBy;
	}

	public Long getId() {
		return id;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public void setUpdatedBy(User updatedBy) {
		this.updatedBy = updatedBy;
	}

	@PrePersist
	protected void onCreate()
	{
		this.created = this.updated = new Date();
		this.createdBy = this.updatedBy = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
	
	@PreUpdate
	public void onUpdate()
	{
		this.updated = new Date();
		this.updatedBy = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
