package com.example.hs.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "motif")
public class Motif extends TrackingPersistentObject {
	private Subordinate subordinate;
	private Map<Language, MotifText> motifTextMap = new HashMap<Language, MotifText>();

	public Motif() {
		super();
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "motif_id", referencedColumnName = "id")
	@MapKey(name = "language")
	protected Map<Language, MotifText> getMotifTextMap() {
		return motifTextMap;
	}

	protected void setMotifTextMap(Map<Language, MotifText> motifTextMap) {
		this.motifTextMap = motifTextMap;
	}

	@Transient
	public String getMotifName() {
		LocalizedText localText = getMotifTextMap().get(Language.EN);
		if (localText != null) {
			return localText.getTextValue();
		}
		return null;
	}

	@Transient
	public void setMotifName(String name) {
		MotifText localText = getMotifTextMap().get(Language.EN);
		if (localText == null) {
			localText = new MotifText();
			localText.setMotif(this);
			localText.setLanguage(Language.EN);
			localText.setTextValue(name);
			getMotifTextMap().put(Language.EN, localText);
		} else {
			localText.setTextValue(name);
		}
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "subordinate_id")
	public Subordinate getSubordinate() {
		return subordinate;
	}

	public void setSubordinate(Subordinate subordinate) {
		this.subordinate = subordinate;
	}
}