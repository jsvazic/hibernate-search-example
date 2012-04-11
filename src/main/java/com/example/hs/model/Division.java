package com.example.hs.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "division")
public class Division extends TrackingPersistentObject {
	private Map<Language, DivisionText> divisionTextMap = new HashMap<Language, DivisionText>();

	public Division() {
		super();
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "division_id", referencedColumnName = "id")
	@MapKey(name = "language")
	protected Map<Language, DivisionText> getDivisionTextMap() {
		return divisionTextMap;
	}

	protected void setDivisionTextMap(Map<Language, DivisionText> divisionTextMap) {
		this.divisionTextMap = divisionTextMap;
	}

	@Transient
	public String getDivisionName() {
		LocalizedText localText = getDivisionTextMap().get(Language.EN);
		if (localText != null) {
			return localText.getTextValue();
		}
		return null;
	}

	@Transient
	public void setDivisionName(String name) {
		// Attempt to get the existing text object from the map if it exists to update it directly, you can't just add
		// to the map and expect a replacement with hibernate.
		DivisionText localText = getDivisionTextMap().get(Language.EN);
		if (localText == null) {
			localText = new DivisionText();
			localText.setDivision(this);
			localText.setLanguage(Language.EN);
			localText.setTextValue(name);
			getDivisionTextMap().put(Language.EN, localText);
		} else {
			localText.setTextValue(name);
		}
	}
}