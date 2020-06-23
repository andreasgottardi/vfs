package at.goasystems.vfs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VfsApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(VfsApplicationTests.class);

	@Value("${at.goasystems.vfs.repodirpath}")
	private String repodirpath;

	/**
	 * This is a SpringBoot test. Application is configured to initialize a Git
	 * repository when started and if it does not already exist. Therefore the test
	 * does not create one. Git initialization logic is tested in GitTest.java
	 * independent of the SpringBoot application.
	 */
	@Test
	void gitInitTest() {
		logger.debug("Git test started.");
		File repodir = new File(repodirpath);
		assertTrue(repodir.exists());
		assertTrue(new File(repodir, ".git").exists());
		logger.debug("Git test finished.");
	}
}
