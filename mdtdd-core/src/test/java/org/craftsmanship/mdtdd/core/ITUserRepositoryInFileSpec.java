package org.craftsmanship.mdtdd.core;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.craftsmanship.mdtdd.core.User;
import org.craftsmanship.mdtdd.core.UserNotExistsException;
import org.craftsmanship.mdtdd.core.UserRepository;
import org.craftsmanship.mdtdd.core.UserRepositoryInFile;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ITUserRepositoryInFileSpec {

	private UserRepository repository;

	@Before
	public void setUp() {
		repository = new UserRepositoryInFile();
	}

	@After
	public void tearDown() {

		File dirBase = new File(".");
		File[] files = dirBase.listFiles( new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.startsWith("@");
			}
		});
		for( File file : files ) {
			file.delete();
		}
	}

	@Test( expected = UserNotExistsException.class )
	public void no_existe_usuario_en_el_sistema() {

		repository.findByName("@nombre");
	}

	@Test
	public void existe_usuario_en_el_sistema() {

		String name = "@nombre";
		try ( FileWriter fw = new FileWriter( new File( name ) ) ) {
			fw.write("");
		} catch (IOException e) {
			Assert.fail( e.toString() );
		}

		Assert.assertNotNull( repository.findByName(name) );
	}

	@Test
	public void añadir_un_usuario_de_forma_durable() {

		String name = "@nombre";

		repository.add( new User( name ) );

		Assert.assertTrue( new File( name ).exists() );
	}

	@Test
	public void registrar_un_usuario_con_seguidores_de_forma_durable() throws FileNotFoundException, IOException {

		// given
		String name = "name";
		User user = new User(name);
		String followerName1 = "follower1";
		user.follow( new User( followerName1 ) );
		String followerName2 = "follower2";
		user.follow( new User( followerName2 ) );

		// when
		repository.add(user);

		// then
		Set<String> usersName = new HashSet<>();
		try ( BufferedReader br = new BufferedReader( new FileReader( new File( user.name() ) ) ) ) {
			String line;
			while( ( line = br.readLine() ) != null ) {
				usersName.add( line );
			}
		}

		Set<String> followerNames = new HashSet<>();
		followerNames.add( followerName1 );
		followerNames.add( followerName2 );

		Assert.assertEquals( followerNames, usersName );
	}
}
