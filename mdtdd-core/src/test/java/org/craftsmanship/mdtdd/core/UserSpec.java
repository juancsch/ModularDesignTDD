package org.craftsmanship.mdtdd.core;

import static org.junit.Assert.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class UserSpec {

	@Test
	public void consulta_de_usuarios_que_sigue_un_usuario_dado() {

		User user = new User("@name");
		user.follow(new User("@juancsch"));
		user.follow(new User("@pasku"));

		@SuppressWarnings("serial")
		Set<User> following = new HashSet<User>() {{
			add( new User( "@juancsch" ) );
			add( new User( "@pasku" ) );
		}};

		assertEquals( following, user.following() );
	}
}
