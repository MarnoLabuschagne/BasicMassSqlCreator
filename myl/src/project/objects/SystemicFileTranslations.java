package project.objects;

public class SystemicFileTranslations {
	private String[] parameters;
	private String id;
	private String name;
	private String description;
	private String culture;
	
	public SystemicFileTranslations(String id, String name, String description, String culture) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.culture = culture;
		parameters = new String[] {"SystemicFileId", "Name", "Description", "Culture"};
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
				", '" + name + "'" +
				", '" + description + "'" +
				", '" + culture + "')";
		
		return result;
	}

}
