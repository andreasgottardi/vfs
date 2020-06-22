package at.goasystems.vfs;

import java.io.File;

import javax.annotation.PostConstruct;

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
		GitManager gm = new GitManager();
		gm.initGitDir(repodir);
		logger.debug("Application loaded.");
	}
}