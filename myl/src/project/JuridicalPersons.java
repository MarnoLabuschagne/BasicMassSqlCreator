package project;
//INSERT INTO JuridicalPersons(Id, ResourceIdentifier, IsValidRespondent, IsActive, JuridicalPersonTypeId)
//VALUES
//	  ('67a03d71-bb6f-4f34-b6a1-6c3b3aa4f4de', 'random 40 characters', 1, 1, 1),
// ( . . .		. . .);

public class JuridicalPersons {
	private String[] parameters;
	private String Id;
	private String resourceIdentifier;
	private boolean isValidRespondent;
	private boolean isActive;
	private int juridicalPersonTypeId;
	
	
	public JuridicalPersons(String IdIn, 
			String resourceIdentifierIn, 
			boolean isValidRespondentIn, 
			boolean isActiveIn, 
			int juridicalPersonTypeIdIn) {
		Id = IdIn;
		resourceIdentifier = resourceIdentifierIn;
		isValidRespondent = isValidRespondentIn;
		isActive = isActiveIn;
		juridicalPersonTypeId = juridicalPersonTypeIdIn;
		
		parameters = new String[]{"Id", "ResourceIdentifier", "IsValidRespondent", "IsActive", "JuridicalPersonTypeId"};
	}

	public String getId() {
		return Id;
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
		String result = "('" + Id + "'" + 
				", '" + resourceIdentifier + "'" + 
				", " + (isValidRespondent ? 1 : 0) +
				", " + (isActive ? 1 : 0) +
				", " + juridicalPersonTypeId + ")";
		
		return result;
	}
}
