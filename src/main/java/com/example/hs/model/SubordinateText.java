package com.example.hs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subordinate_text")
public class SubordinateText extends LocalizedText {
	private Subordinate subordinate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subordinate_id", nullable = false)
	public Subordinate getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(Subordinate subordinate) {
		this.subordinate = subordinate;
	}
}