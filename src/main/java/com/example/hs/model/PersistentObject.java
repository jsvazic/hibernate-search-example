package com.example.hs.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@MappedSuperclass
public abstract class PersistentObject {
	private Long id;
	
	protected PersistentObject() {
	}

	@Id
	@GeneratedValue(generator = "hibseq", strategy = GenerationType.TABLE)
	@GenericGenerator(name = "hibseq", strategy = "enhanced-table", parameters = {
			@Parameter(name = "table_name", value = "sequence_generator"),
			@Parameter(name = "value_column_name", value = "sequence_next_hi_value "),
			@Parameter(name = "segment_column_name", value = "sequence_name "),
			@Parameter(name = "prefer_entity_table_as_segment_value", value = "true"),
			@Parameter(name = "increment_size", value = "100") })
	@Column(name = "id")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
