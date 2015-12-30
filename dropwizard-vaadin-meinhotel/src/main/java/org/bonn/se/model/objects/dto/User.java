package org.bonn.se.model.objects.dto;

import java.util.List;

import org.bonn.se.model.dao.RoleDAO;

public class User {
	private String login = null;
	private String vorname = null;
	private List<Role> roles = null;

	public String getLogin() {
		return login;
	}

	private void getRoles() {
		roles = RoleDAO.getInstance().getRolesForUser(this);
	}

	public String getVorname() {
		return vorname;
	}

	public boolean hasRole(String role) {
		if (roles == null) {
			getRoles();
		}

		for (Role role1 : roles) {
			if (role1.getBezeichnung().equals(role)) {
				return true;
			}
		}
		return false;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setVorname(String vorname) {
		this.vorname = vorname;
	}
}
