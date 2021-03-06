package at.goasystems.vfs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import at.goasystems.vfs.com.Request;
import at.goasystems.vfs.com.Response;

@RestController
public class ManagementController {

	private static Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@Autowired
	Controller controller;

	@PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
	public String create(@RequestBody String body) {
		logger.debug("Create endpoint called.");
		logger.debug("Create body received: {}", body);
		Gson gson = GsonFactory.getGson();
		Request request = gson.fromJson(body, Request.class);
		Response response = controller.evaluateCreateRequest(request);
		if (!response.hasErrors()) {
			controller.createResource(request);
		}
		return gson.toJson(response);
	}

	@GetMapping(value = "/read/{key}/{lang}")
	public String read(@PathVariable("key") String key, @PathVariable("lang") String lang) {
		logger.debug("Read endpoint called.");
		logger.debug("Key: {}, Lang: {}", key, lang);
		return "";
	}

	@PostMapping(value = "/update")
	public String update(@RequestBody String request) {
		logger.debug("Update endpoint called.");
		logger.debug("Update body received: {}", request);
		return "";
	}

	@PostMapping(value = "/delete")
	public String delete(@RequestBody String request) {
		logger.debug("Delete endpoint called.");
		logger.debug("Delete body received: {}", request);
		return "";
	}
}
