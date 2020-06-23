package at.goasystems.vfs;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ConfTest {

	private static Logger logger = LoggerFactory.getLogger(ConfTest.class);

	@Test
	void testConf() {
		String langdeffile = System.getProperty("language.definition.file");
		assertNotNull(langdeffile);
		logger.debug("Configuration: {}", langdeffile);
	}
}
