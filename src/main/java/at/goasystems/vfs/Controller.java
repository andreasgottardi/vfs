package at.goasystems.vfs;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import at.goasystems.vfs.com.Request;
import at.goasystems.vfs.com.Response;

@Component
public class Controller {

	private static Logger logger = LoggerFactory.getLogger(Controller.class);

	@Value("${at.goasystems.vfs.repodirpath}")
	private String repodirpath;

	public void createResource(Request request) {
		logger.debug("Creation of resource {} requested.", request.getId());
	}

	public Response evaluateCreateRequest(Request request) {
		Response response = new Response();
		File resourcedir = new File(repodirpath, request.getId());
		if (resourcedir.exists()) {
			response.addError("Directory already exists.");
		}
		return response;
	}
}
