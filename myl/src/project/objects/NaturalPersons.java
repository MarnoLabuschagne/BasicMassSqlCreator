package project.objects;

public class NaturalPersons {
	private String[] parameters;
	private String id;
	private String resourceIdentifier;
	private String givenNames;
	private String surnames;
	private int gender;
	
	public NaturalPersons(String id, String resourceIdentifier, String givenNames, String surnames, String gender) {
		this.id = id;
		this.resourceIdentifier = resourceIdentifier;
		this.givenNames = givenNames;
		this.surnames = surnames;
		this.gender = Integer.parseInt(gender);
		parameters = new String[] {"Id", "ResourceIdentifier", "GivenNames", "Surnames", "GenderId"};
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
				", '" + resourceIdentifier + "'" +
				", '" + givenNames + "'" +
				", '" + surnames + "'" +
				", " + gender + ")";
		
		return result;
	}
}