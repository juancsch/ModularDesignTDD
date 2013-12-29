package org.craftsmanship.mdtdd.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
	TwitterInFileSpec.class,
	TwitterInMemorySpec.class,
	ITUserRepositoryInFileSpec.class
})
public class TwitterSpecs {
}