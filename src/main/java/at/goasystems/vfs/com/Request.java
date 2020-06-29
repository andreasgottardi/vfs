package at.goasystems.vfs.com;

public class Request {

	private String id;
	private MetaData metadata;
	private Origin origin;
	private LocalizedFile[] localizedfiles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MetaData getMetadata() {
		return metadata;
	}

	public void setMetadata(MetaData metadata) {
		this.metadata = metadata;
	}

	public Origin getOrigin() {
		return origin;
	}

	public void setOrigin(Origin origin) {
		this.origin = origin;
	}

	public LocalizedFile[] getLocalizedfiles() {
		return localizedfiles;
	}

	public void setLocalizedfiles(LocalizedFile[] localizedfiles) {
		this.localizedfiles = localizedfiles;
	}

}
