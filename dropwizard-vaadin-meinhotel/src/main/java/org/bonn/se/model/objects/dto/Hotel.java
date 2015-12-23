package org.bonn.se.model.objects.dto;

import java.io.Serializable;

public class Hotel implements Serializable {

	private String name;

	private Integer id;

	private String ort;

	private String description;

	public Hotel() {
	}

	public Hotel(String name, Integer id, String ort, String description) {
		this.name = name;
		this.id = id;
		this.ort = ort;
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOrt() {
		return ort;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}
}
