package at.goasystems.vfs;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.UUID;

import org.eclipse.jgit.api.Git;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GitTest {

	private static Logger logger = LoggerFactory.getLogger(GitTest.class);

	@Test
	void testGit() {
		logger.debug("Git test initialized.");
		GitManager gm = new GitManager();
		File gitdir = new File(System.getProperty("java.io.tmpdir"), UUID.randomUUID().toString());
		if (gitdir.exists()) {
			gm.removeDir(gitdir);
		}
		Git git = gm.initGitDir(gitdir);
		assertTrue(gitdir.exists());
		assertTrue(new File(gitdir, ".git").exists());
		String filename = "readme.txt";
		String content = "Description";
		gm.initDescription(git, filename, content, "Master branch with readme initialized.");
		assertTrue(new File(gitdir, filename).exists());
		assertEquals(new File(gitdir, filename).length(), content.length());
		gm.removeDir(gitdir);
		assertTrue(!new File(gitdir, ".git").exists());
		assertTrue(!new File(gitdir, filename).exists());
		assertTrue(!gitdir.exists());
		logger.debug("Git test finished.");
	}
}
