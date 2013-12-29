package org.craftsmanship.mdtdd.core;

import java.io.File;
import java.io.FilenameFilter;

import org.craftsmanship.mdtdd.core.UserServiceFactory;
import org.junit.After;
import org.junit.Before;

public class TwitterInFileSpec extends TwitterSpec {

	@Before
	public void setUp() {

		service = UserServiceFactory.createUserService();
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
}