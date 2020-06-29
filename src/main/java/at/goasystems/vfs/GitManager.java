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

	/**
	 * Initializes the repository.
	 * 
	 * @param gitdir Directory where to initialize the repository.
	 * @return Repository reference or null in case of error
	 */
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

	/**
	 * Initializes the description file only fi the file does not already exist.
	 * 
	 * @param git         The repository
	 * @param filename    The filename
	 * @param description The content
	 */
	public void initDescription(Git git, String filename, String description, String commitmessage) {
		File workingtree = git.getRepository().getDirectory().getParentFile();
		File descriptionfile = new File(workingtree, filename);
		if (!descriptionfile.exists()) {
			try (FileOutputStream fos = new FileOutputStream(descriptionfile)) {
				fos.write(description.getBytes(StandardCharsets.UTF_8));
				git.add().addFilepattern("readme.txt").call();
				git.commit().setMessage(commitmessage).call();
			} catch (IOException | GitAPIException e) {
				logger.error("Error commiting description.", e);
			}
		} else {
			logger.error("File {} does already exist. Will not overwrite it.", descriptionfile.getAbsolutePath());
		}
	}

	/**
	 * Removes a directory recursively.
	 * 
	 * @param directory
	 * @return true if successful. Else false.
	 */
	public boolean removeDir(File directory) {
		try (Stream<Path> stream = Files.walk(directory.getParentFile().toPath())) {
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
