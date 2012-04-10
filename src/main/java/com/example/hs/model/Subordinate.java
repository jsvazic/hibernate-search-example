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
@Table(name = "subordinate")
public class Subordinate extends TrackingPersistentObject {
	private Division division;
	private Map<Language, SubordinateText> subordinateTextMap = new HashMap<Language, SubordinateText>();

	public Subordinate() {
		super();
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "subordinate_id", referencedColumnName = "id")
	@MapKey(name = "language")
	protected Map<Language, SubordinateText> getSubordinateTextMap() {
		return subordinateTextMap;
	}

	protected void setSubordinateTextMap(Map<Language, SubordinateText> subordinateTextMap) {
		this.subordinateTextMap = subordinateTextMap;
	}

	@Transient
	public String getSubordinateName() {
		LocalizedText localText = getSubordinateTextMap().get(Language.EN);
		if (localText != null) {
			return localText.getTextValue();
		}

		return null;
	}

	@Transient
	public void setSubordinateName(String name) {
		SubordinateText localText = getSubordinateTextMap().get(Language.EN);
		if (localText == null) {
			localText = new SubordinateText();
			localText.setSubordinate(this);
			localText.setLanguage(Language.EN);
			localText.setTextValue(name);
			getSubordinateTextMap().put(Language.EN, localText);
		} else {
			localText.setTextValue(name);
		}
	}

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "division_id")
	public Division getDivision() {
		return division;
	}

	public void setDivision(Division division) {
		this.division = division;
	}
}