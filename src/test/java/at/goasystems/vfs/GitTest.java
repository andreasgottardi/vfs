package at.goasystems.vfs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GitTest {

	private static final Logger logger = LoggerFactory.getLogger(GitTest.class);

	@Value("${at.goasystems.vfs.repodirpath}")
	private String repodirpath;

	@Test
	void gitInitTest() {
		logger.debug("Git test started.");
		File repodir = new File(repodirpath);
		GitManager gm = new GitManager();
		assertTrue(repodir.exists());
		assertTrue(new File(repodir, ".git").exists());
		assertTrue(gm.removeGitDir(repodir));
		logger.debug("Git test finished.");
	}
}
