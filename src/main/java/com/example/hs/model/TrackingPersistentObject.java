package com.example.hs.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class TrackingPersistentObject extends PersistentObject {
	private Date createDate;
	private Date lastModifyDate;

	protected TrackingPersistentObject() {
		super();
		setCreateDate(new Date());
	}
	
	@Column(name = "last_modify_date")
	@Version
	public Date getLastModifyDate() {
		return lastModifyDate;
	}

	public void setLastModifyDate(Date lastModifyDate) {
		this.lastModifyDate = lastModifyDate;
	}

	@Column(name = "create_date")
	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
