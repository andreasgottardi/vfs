package at.goasystems.vfs;

import java.io.File;

import javax.annotation.PostConstruct;

import org.eclipse.jgit.api.Git;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VfsApplication {

	private static final Logger logger = LoggerFactory.getLogger(VfsApplication.class);

	@Value("${at.goasystems.vfs.repodirpath}")
	private String repodirpath;

	public static void main(String[] args) {
		SpringApplication.run(VfsApplication.class, args);
	}

	@PostConstruct
	private void init() {
		File repodir = new File(repodirpath);
		if (!repodir.exists()) {
			GitManager gm = new GitManager();
			Git git = gm.initGitDir(repodir);
			gm.commitDescription(git);
			logger.debug("Git directory {} created.", repodir);
		} else {
			logger.debug("Repository directory {} already exists.", repodir);
		}
	}
}