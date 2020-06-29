package at.goasystems.vfs;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStreamReader;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import at.goasystems.vfs.com.Request;

class RequestTest {

	private static Logger logger = LoggerFactory.getLogger(RequestTest.class);

	@Test
	void test() {

		Gson gson = new GsonBuilder().create();
		Request request = gson.fromJson(new InputStreamReader(RequestTest.class.getResourceAsStream("/test1.json")),
				Request.class);
		assertEquals(2, request.getLocalizedfiles().length);
		logger.debug("Request for id {} generated.", request.getId());
	}
}
