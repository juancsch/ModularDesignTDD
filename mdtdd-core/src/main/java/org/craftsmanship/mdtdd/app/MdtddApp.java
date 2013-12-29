package org.craftsmanship.mdtdd.app;

import java.util.Set;

import org.craftsmanship.mdtdd.core.UserService;
import org.craftsmanship.mdtdd.core.UserServiceFactory;

public class MdtddApp {

	private UserService service;

	public MdtddApp() {
		this.service = UserServiceFactory.createUserServiceMen();
	}

	public void registration( String userName ) {

		service.registerUser(userName);
	}

	public void follow( String following, String follower ) {

		service.follow(following, follower);
	}

	public Set<UserDTO> following( String follower ) {

		return UserMapper.mapperUser( service.getUserByName(follower).following() );
	}
}
