package org.bonn.se.model.objects.entities;

import java.util.Date;

import org.bonn.se.model.objects.dto.Hotel;
import org.bonn.se.model.objects.dto.User;

public class Booking {
	private int id;
	private Date anreise = null;
	private Date abreise = null;
	private String iban = null;
	private int number;
	private Hotel hotel;
	private User user;
	private Date datumBuchung = null;

	public Date getAbreise() {
		return abreise;
	}

	public Date getAnreise() {
		return anreise;
	}

	public Date getDatumBuchung() {
		return datumBuchung;
	}

	public Hotel getHotel() {
		return hotel;
	}

	public String getIban() {
		return iban;
	}

	public int getId() {
		return id;
	}

	public int getNumber() {
		return number;
	}

	public User getUser() {
		return user;
	}

	public void setAbreise(Date abreise) {
		this.abreise = abreise;
	}

	public void setAnreise(Date anreise) {
		this.anreise = anreise;
	}

	public void setDatumBuchung(Date datumBuchung) {
		this.datumBuchung = datumBuchung;
	}

	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
