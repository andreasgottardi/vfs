package at.goasystems.vfs;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class GitManager {

	private static final Logger logger = LoggerFactory.getLogger(GitManager.class);

	public void initGitDir(File gitdir) {
		if (!gitdir.exists()) {
			try (Git git = Git.init().setDirectory(gitdir).call();) {
				logger.info("Git directory \"{}\" exists.", git.getRepository().getDirectory().getAbsolutePath());
			} catch (IllegalStateException | GitAPIException e) {
				logger.error("Error creating repository.", e);
			}
		}
	}

	public boolean removeGitDir(File gitdir) {
		try (Stream<Path> stream = Files.walk(gitdir.getParentFile().toPath())) {
			stream.sorted(Comparator.reverseOrder()).map(Path::toFile).forEach(File::delete);
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
