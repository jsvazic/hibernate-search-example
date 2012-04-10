package com.example.hs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "motif_text")
public class MotifText extends LocalizedText {
	private Motif motif;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "motif_id", nullable = false)
	public Motif getMotif() {
		return motif;
	}

	public void setMotif(Motif motif) {
		this.motif = motif;
	}
}
