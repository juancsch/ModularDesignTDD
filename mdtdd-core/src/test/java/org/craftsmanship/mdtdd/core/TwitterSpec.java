package org.craftsmanship.mdtdd.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public abstract class TwitterSpec {

	protected UserService service;

	@Test( expected = UserExistsException.class )
	public void registro_erroneo_usuario() {

		service.registerUser( "@juancsch" );
		service.registerUser( "@juancsch" );
	}

	@Test
	public void registro_correcto_usuario() {

		assertTrue( service.registerUser( "@juancsch" ) );
	}

	@Test
	public void usuario_sigue_otro_usuario() {

		service.registerUser( "@juancsch" );
		service.registerUser( "@satrapa" );
		assertTrue( service.follow( "@juancsch", "@satrapa" ) );
	}

	@Test( expected = UserNotExistsException.class )
	public void usuario_registrado_sigue_otro_usuario_no_registrado() {

		service.registerUser( "@juancsch" );
		service.follow( "@juancsch", "@satrapa" );
	}

	@Test( expected = UserNotExistsException.class )
	public void usuario_no_registrado_sigue_otro_usuario_registrado() {

		service.registerUser( "@satrapa" );
		service.follow( "@juancsch", "@satrapa" );
	}

	@Test
	public void consulta_de_usuarios_que_sigue_un_usuario_dado() {

		service.registerUser( "@satrapa" );
		service.registerUser( "@juancsch" );
		service.registerUser( "@pasku" );

		service.follow( "@satrapa", "@juancsch" );
		service.follow( "@satrapa", "@pasku" );

		@SuppressWarnings("serial")
		Set<User> following = new HashSet<User>() {{
			add( new User( "@juancsch" ) );
			add( new User( "@pasku" ) );
		}};

		assertEquals( following, service.getUserByName( "@satrapa" ).following() );
	}
}