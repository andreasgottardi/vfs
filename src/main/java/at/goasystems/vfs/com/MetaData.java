package at.goasystems.vfs.com;

public class MetaData {

	private String originmimetype;
	private String originextension;
	private String resourcemimetype;
	private String resourceextension;
	private boolean locked;

	public String getOriginmimetype() {
		return originmimetype;
	}

	public void setOriginmimetype(String originmimetype) {
		this.originmimetype = originmimetype;
	}

	public String getOriginextension() {
		return originextension;
	}

	public void setOriginextension(String originextension) {
		this.originextension = originextension;
	}

	public String getResourcemimetype() {
		return resourcemimetype;
	}

	public void setResourcemimetype(String resourcemimetype) {
		this.resourcemimetype = resourcemimetype;
	}

	public String getResourceextension() {
		return resourceextension;
	}

	public void setResourceextension(String resourceextension) {
		this.resourceextension = resourceextension;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}
}
