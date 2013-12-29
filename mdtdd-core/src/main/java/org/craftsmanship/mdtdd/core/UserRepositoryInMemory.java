package org.craftsmanship.mdtdd.core;

import java.util.HashMap;
import java.util.Map;

public class UserRepositoryInMemory implements UserRepository {

	private Map<String, User> users = new HashMap<>();

	@Override
	public User findByName( String userName ) {

		User user = users.get( userName );
		if( user == null ) {
			throw new UserNotExistsException( "No existe el usuario [" + userName + "] en el sistema." );
		}
		return user;
	}

	@Override
	public void add( User user ) {

		users.put( user.name(), user );
	}
}
