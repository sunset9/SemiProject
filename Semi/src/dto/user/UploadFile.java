package dto.user;

public class UploadFile {
	private String origin_name;
	private String stored_name;
	
	@Override
	public String toString() {
		return "UploadFile [origin_name=" + origin_name + ", stored_name=" + stored_name + "]";
	}

	public String getOrigin_name() {
		return origin_name;
	}

	public void setOrigin_name(String origin_name) {
		this.origin_name = origin_name;
	}

	public String getStored_name() {
		return stored_name;
	}

	public void setStored_name(String stored_name) {
		this.stored_name = stored_name;
	}
	
	
}
