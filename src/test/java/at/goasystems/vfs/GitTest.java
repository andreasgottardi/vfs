package at.goasystems.vfs;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
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

		File repodir = new File(repodirpath);

		assertTrue(initGitDir(repodir));
		assertTrue(removeGitDir(repodir));

	}

	private boolean initGitDir(File repodir) {
		try (Git git = Git.init().setDirectory(repodir).call();) {
			File gitdir = git.getRepository().getDirectory();
			logger.info("Git directory \"{}\" exists.", gitdir.getAbsolutePath());
			return true;
		} catch (IllegalStateException | GitAPIException e) {
			logger.error("Error creating repository.", e);
			return false;
		}
	}

	private boolean removeGitDir(File repodir) {
		try {
			Files.walk(repodir.getParentFile().toPath()).sorted(Comparator.reverseOrder()).map(Path::toFile)
					.forEach(File::delete);
			return true;
		} catch (IOException e) {
			logger.error("Error deleting repository.", e);
			return false;
		} catch (NullPointerException e) {
			logger.error("Git was not initialized correctly.", e);
			return false;
		}
	}

}
