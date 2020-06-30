package at.goasystems.vfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ManagementController {

	private static Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@PostMapping(value = "/create")
	public String create() {
		logger.debug("Create endpoint called.");
		return "";
	}

	@GetMapping(value = "/read")
	public String read() {
		logger.debug("Read endpoint called.");
		return "";
	}

	@PostMapping(value = "/update")
	public String update() {
		logger.debug("Update endpoint called.");
		return "";
	}

	@PostMapping(value = "/delete")
	public String delete() {
		logger.debug("Delete endpoint called.");
		return "";
	}
}
