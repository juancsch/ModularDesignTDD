package org.craftsmanship.mdtdd.app;

public class UserDTO {

	private String name;

	public UserDTO() {
		super();
	}

	public UserDTO( String name ) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName( String name ) {
		this.name = name;
	}

	@Override
	public String toString() {
		return name;
	}
}
