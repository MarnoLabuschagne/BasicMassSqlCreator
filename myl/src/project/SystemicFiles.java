package project;

public class SystemicFiles {
	private String[] parameters;
	private String id;
	private String orgId;
	private String resourceIdentifier;
	private boolean locked;
	
	public SystemicFiles(String id, String orgId, String resourceIdentifier, boolean locked) {
		this.id = id;
		this.orgId = orgId;
		this.resourceIdentifier = resourceIdentifier;
		this.locked = locked;
		parameters = new String[] {"Id", "OrganizationalSectionId", "ResourceIdentifier", "Locked"};
	}

	public String getId() {
		return id;
	}
	
	public String getParameters() {
		String result = "";
		for(int i=0; i<parameters.length; i++) {
			if (i>0) {
				result += ", ";
			}
			result += parameters[i];
		}
		return result;
	}

	public String toString() {
		String result = "('" + id + "'" + 
				", '" + orgId + "'" +
				", '" + resourceIdentifier + "'" +
				", '" + (locked ? 1 : 0) + "')";
		
		return result;
	}
}
