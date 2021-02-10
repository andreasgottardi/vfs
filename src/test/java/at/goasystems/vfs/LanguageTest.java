package at.goasystems.vfs;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.GsonBuilder;

import at.goasystems.vfs.com.Language;

class LanguageTest {

	private static Logger logger = LoggerFactory.getLogger(LanguageTest.class);

	@Test
	void testConf() {
		String langdeffile = System.getProperty("at.goasystems.vfs.langdef");
		assertNotNull(
				"The language definition is not supposed to be null. If null, please specify with \"-Dat.goasystems.vfs.langdef=path/to/languages.json\"",
				langdeffile);
		logger.debug("Configuration: {}", langdeffile);

		try (FileInputStream fis = new FileInputStream(new File(langdeffile));
				InputStreamReader isr = new InputStreamReader(fis);) {

			Language[] targetArray = new GsonBuilder().create().fromJson(isr, Language[].class);
			assertEquals(5, targetArray.length);

		} catch (Exception e) {
			logger.error("General_error.", e);
		}

	}
}
