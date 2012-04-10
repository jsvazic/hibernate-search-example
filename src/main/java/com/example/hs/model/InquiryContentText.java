package com.example.hs.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "inquiry_content_text")
public class InquiryContentText extends LocalizedText {
	private Inquiry inquiry;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "inquiry_id", nullable = false, updatable = true)
	public Inquiry getInquiry() {
		return inquiry;
	}

	public void setInquiry(Inquiry inquiry) {
		this.inquiry = inquiry;
	}
}
