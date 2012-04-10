package com.example.hs.model;


import java.io.Serializable;
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

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.FieldBridge;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Table(name = "inquiry")
public class Inquiry extends TrackingPersistentObject implements Serializable {
	
	@Field(name = "content")
	@FieldBridge(impl = com.example.hs.search.LanguageInquiryContentTextBridge.class)
	private Map<Language, InquiryContentText> contentTextMap = new HashMap<Language, InquiryContentText>();
	
	@Field(name = "reason")
	@FieldBridge(impl = com.example.hs.search.LanguageInquiryReasonTextBridge.class)
	private Map<Language, InquiryReasonText> reasonTextMap = new HashMap<Language, InquiryReasonText>();

	private static final long serialVersionUID = 5814677068682193989L;

	private int level;
	
	public Inquiry() {
		super();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "inquiry_id", referencedColumnName = "id")
	@MapKey(name = "language")
	protected Map<Language, InquiryReasonText> getReasonTextMap() {
		return reasonTextMap;
	}

	protected void setReasonTextMap(Map<Language, InquiryReasonText> reasonTextMap) {
		this.reasonTextMap = reasonTextMap;
	}

	@Transient
	public String getReasonText() {
		LocalizedText localText = getReasonTextMap().get(Language.EN);
		if (localText != null) {
			return localText.getTextValue();
		}
		return null;
	}

	@Transient
	public void setReasonText(String name) {
		// Attempt to get the existing text object from the map if it exists to update it directly, you can't just add
		// to the map and expect a replacement with hibernate.
		InquiryReasonText localText = getReasonTextMap().get(Language.EN);
		if (localText == null) {
			localText = new InquiryReasonText();
			localText.setInquiry(this);
			localText.setLanguage(Language.EN);
			localText.setTextValue(name);
			getReasonTextMap().put(Language.EN, localText);
		} else {
			localText.setTextValue(name);
		}
	}

	@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
	@JoinColumn(name = "inquiry_id", referencedColumnName = "id")
	@MapKey(name = "language")
	protected Map<Language, InquiryContentText> getContentTextMap() {
		return contentTextMap;
	}

	protected void setContentTextMap(Map<Language, InquiryContentText> contentTextMap) {
		this.contentTextMap = contentTextMap;
	}

	@Transient
	public String getContentText() {
		LocalizedText localText = getContentTextMap().get(Language.EN);
		if (localText != null) {
			return localText.getTextValue();
		}
		return null;
	}

	@Transient
	public void setContentText(String name) {
		InquiryContentText localText = getContentTextMap().get(Language.EN);
		if (localText == null) {
			localText = new InquiryContentText();
			localText.setInquiry(this);
			localText.setLanguage(Language.EN);
			localText.setTextValue(name);
			getContentTextMap().put(Language.EN, localText);
		} else {
			localText.setTextValue(name);
		}
	}

	private Motif motif;

	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "motif_id")
	public Motif getMotif() {
		return motif;
	}

	public void setMotif(Motif motif) {
		this.motif = motif;
	}
}
