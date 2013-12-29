package org.craftsmanship.mdtdd.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class UserRepositoryInFile implements UserRepository {

	@Override
	public User findByName( String userName ) {

		User user = new User( userName );
		for( User follower : getFollowers( user ) ) {
			user.follow( follower );
		}
		return user;
	}

	private Set<User> getFollowers( User user ) {

		Set<User> users = new HashSet<>();
		try ( BufferedReader br = new BufferedReader( new FileReader( new File( user.name() ) ) ) ) {
			String line;
			while( ( line = br.readLine() ) != null ) {
				users.add( new User( line ) );
			}
		} catch (FileNotFoundException e) {
			throw new UserNotExistsException("No existe el usuario [" + user.name() + "] en el sistema.", e);
		} catch (IOException e) {
			throw new RuntimeException("Error obtener los seguidores del usuario " + user, e);
		}
		return users;
	}

	@Override
	public void add( User user ) {

		try ( BufferedWriter bw = new BufferedWriter( new FileWriter( new File( user.name() ) ) ) ) {
			bw.write( "" );
			for( User follower : user.following() ) {
				bw.append( follower.name() );
				bw.newLine();
			}
		} catch (IOException e) {
			throw new RuntimeException("Error al crear el usuario " + user, e);
		}
	}
}
