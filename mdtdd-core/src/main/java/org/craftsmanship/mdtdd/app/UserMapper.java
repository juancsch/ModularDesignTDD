package org.craftsmanship.mdtdd.app;

import java.util.HashSet;
import java.util.Set;

public class UserMapper {

	public static Set<UserDTO> mapperUser( Set<org.craftsmanship.mdtdd.core.User> entities ) {

		Set<UserDTO> users = new HashSet<>( entities.size() );
		for( org.craftsmanship.mdtdd.core.User entity : entities ) {
			users.add(new UserDTO( entity.name() ));
		}
		return users;
	}
}
