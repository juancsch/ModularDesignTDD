package org.craftsmanship.mdtdd.core;

public class UserServiceFactory {

	public static UserService createUserService() {

		return new UserService( new UserRepositoryInFile() );
	}

	public static UserService createUserServiceMen() {

		return new UserService( new UserRepositoryInMemory() );
	}
}
