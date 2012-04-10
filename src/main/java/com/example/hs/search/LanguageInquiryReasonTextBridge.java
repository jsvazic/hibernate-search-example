package com.example.hs.search;


import java.util.Map;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import com.example.hs.model.Language;
import com.example.hs.model.InquiryReasonText;

public class LanguageInquiryReasonTextBridge implements FieldBridge {
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		Map<Language, InquiryReasonText> textMap = (Map<Language, InquiryReasonText>) value;
		
		for (Language lang : textMap.keySet()) {
			luceneOptions.addFieldToDocument(name + "." + lang.name(), textMap.get(lang).getTextValue(), document);
		}
	}
}