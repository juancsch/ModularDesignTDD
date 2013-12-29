package org.craftsmanship.mdtdd.core;

import org.craftsmanship.mdtdd.core.UserServiceFactory;
import org.junit.Before;

public class TwitterInMemorySpec extends TwitterSpec {

	@Before
	public void setUp() {

		service = UserServiceFactory.createUserServiceMen();
	}
}