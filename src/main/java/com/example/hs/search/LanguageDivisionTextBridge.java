package com.example.hs.search;

import java.util.Map;

import org.apache.lucene.document.Document;
import org.hibernate.search.bridge.FieldBridge;
import org.hibernate.search.bridge.LuceneOptions;

import com.example.hs.model.DivisionText;
import com.example.hs.model.Language;

public class LanguageDivisionTextBridge implements FieldBridge {
	public void set(String name, Object value, Document document, LuceneOptions luceneOptions) {
		@SuppressWarnings("unchecked")
		Map<Language, DivisionText> textMap = (Map<Language, DivisionText>) value;
		
		for (Language lang : textMap.keySet()) {
			luceneOptions.addFieldToDocument(name + "." + lang.name(), textMap.get(lang).getTextValue(), document);
		}
	}
}