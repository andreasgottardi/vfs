package at.goasystems.vfs;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VfsApplicationTests {

	private static final Logger logger = LoggerFactory.getLogger(VfsApplicationTests.class);

	@Test
	void contextLoads() {
		logger.debug("Default test initialized.");
		assertTrue(true);
	}

}
