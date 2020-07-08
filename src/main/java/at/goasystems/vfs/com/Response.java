package at.goasystems.vfs.com;

import java.util.ArrayList;
import java.util.List;

public class Response {

	private List<String> warnings;
	private List<String> errors;

	public Response() {
		this.warnings = new ArrayList<>();
		this.errors = new ArrayList<>();
	}

	public List<String> getWarnings() {
		return warnings;
	}

	public void setWarnings(List<String> warnings) {
		this.warnings = warnings;
	}

	public void addWarning(String warning) {
		this.warnings.add(warning);
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public void addError(String error) {
		this.errors.add(error);
	}

	public boolean hasWarnings() {
		return !this.warnings.isEmpty();
	}

	public boolean hasErrors() {
		return !this.errors.isEmpty();
	}

}
