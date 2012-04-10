package com.example.hs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "division_text")
public class DivisionText extends LocalizedText implements Serializable {
	private static final long serialVersionUID = 2507550568508102524L;
	private Division division;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "division_id", nullable = false)
	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
}