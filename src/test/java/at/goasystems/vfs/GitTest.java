package at.goasystems.vfs;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.GregorianCalendar;

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
		File gitdir = new File(System.getProperty("java.io.tmpdir"),
				Long.toString(new GregorianCalendar().getTimeInMillis()));
		if (gitdir.exists()) {
			gm.removeDir(gitdir);
		}
		Git git = gm.initGitDir(gitdir);
		assertTrue(gitdir.exists());
		assertTrue(new File(gitdir, ".git").exists());
		gm.commitDescription(git);
		assertTrue(new File(gitdir, "readme.txt").exists());
		gm.removeDir(gitdir);
		assertTrue(!new File(gitdir, ".git").exists());
		assertTrue(!new File(gitdir, "readme.txt").exists());
		assertTrue(!gitdir.exists());
		logger.debug("Git test finished.");
	}
}
