package org.craftsmanship.mdtdd.core;

import java.util.HashSet;
import java.util.Set;

public class User {

	private String name;
	private Set<User> following;

	public User( String name ) {
		this.name = name;
		this.following = new HashSet<User>();
	}

	public String name() {
		return name;
	}

	public Set<User> following() {

		return following;
	}

	public boolean follow( User follower ) {

		return following.add( follower );
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
