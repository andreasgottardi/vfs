package at.goasystems.vfs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

	public Git initGitDir(File gitdir) {
		Git git = null;
		if (!gitdir.exists()) {
			try {
				git = Git.init().setDirectory(gitdir).call();
				logger.info("Git directory \"{}\" does not exist. Initiating.",
						git.getRepository().getDirectory().getAbsolutePath());
			} catch (IllegalStateException | GitAPIException e) {
				logger.error("Error creating repository.", e);
			}
		} else if (gitdir.exists() && !new File(gitdir, ".git").exists()) {
			try {
				git = Git.open(gitdir);
				git.add().addFilepattern(".").call();
				git.commit().setMessage("Existing directory versioned.").call();
			} catch (IOException | GitAPIException e) {
				logger.error("Error versioning existing directory.", e);
			}
		} else {
			logger.error("This code should not have been reached. Please contact the developer.");
		}
		return git;
	}

	public void commitDescription(Git git) {
		File workingtree = git.getRepository().getDirectory().getParentFile();
		try (FileOutputStream fos = new FileOutputStream(new File(workingtree, "readme.txt"))) {
			fos.write("This is the working directory containing all resources.".getBytes(StandardCharsets.UTF_8));
			git.add().addFilepattern("readme.txt").call();
			git.commit().setMessage("Master branch with readme initialized.").call();
		} catch (IOException | GitAPIException e) {
			logger.error("Error commiting description.", e);
		}
	}

	public boolean removeDir(File gitdir) {
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
