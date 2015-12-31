package org.bonn.se.model.objects.dto;

import java.util.Date;

public class BookingRequest {
	private Date anreise = null;
	private Date abreise = null;
	private String iban = null;
	private int number;
	private Hotel hotel;

	public Date getAbreise() {
		return abreise;
	}

	public Date getAnreise() {
		return anreise;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public String getIban() {
		return iban;
	}

	public int getNumber() {
		return number;
	}

	public void setAbreise(Date abreise) {
		this.abreise = abreise;
	}

	public void setAnreise(Date anreise) {
		this.anreise = anreise;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setNumber(int number) {
		this.number = number;
	}
}
