package at.goasystems.vfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.goasystems.vfs.com.Request;

public class Controller {

	private static Logger logger = LoggerFactory.getLogger(Controller.class);

	public void createResource(Request request) {
		logger.debug("Creation of resource {} requested.", request.getId());
	}
}
