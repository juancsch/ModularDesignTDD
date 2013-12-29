package org.craftsmanship.mdtdd.core;

public interface UserRepository {

	public abstract User findByName(String userName);

	public abstract void add(User user);
}