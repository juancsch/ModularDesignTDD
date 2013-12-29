package org.craftsmanship.mdtdd.core;


public class UserService {

	private UserRepository repository;

	public UserService( UserRepository repository ) {

		this.repository = repository;
	}

	public boolean registerUser( String userName ) {

		try {
			this.getUserByName( userName );
			throw new UserExistsException( "Ya existe el usuario " + userName + " en el sistema." );
		} catch ( UserNotExistsException unee ) {
			repository.add( new User( userName ) );
			return true;
		}
	}

	public boolean follow( String follower, String following ) {

		User userFollower = this.getUserByName( follower );

		userFollower.follow( this.getUserByName( following ) );

		repository.add( userFollower );
		return true;
	}

	public User getUserByName( String follower ) {

		return repository.findByName( follower );
	}
}