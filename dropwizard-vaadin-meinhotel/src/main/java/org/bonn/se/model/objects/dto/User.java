package org.bonn.se.model.objects.dto;

public class User {
	private String login = null;
	private String vorname = null;

	public String getLogin() {
		return login;
	}

	public String getVorname() {
		return vorname;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
}
