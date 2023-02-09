package project;
//INSERT INTO JuridicalPersonNameTranslations(JuridicalPersonNameJuridicalPersonId, LegalName, OperatingName, Culture)
//VALUES
//	  ('67a03d71-bb6f-4f34-b6a1-6c3b3aa4f4de', 'Builders Association', 'Builders Association', 'en-ca'),
// ( . . .		. . .);

public class JuridicalPersonNameTranslations {
		private String[] parameters;
		private String JuridicalPersonNameJuridicalPersonId;
		private String LegalName;
		private String OperatingName;
		private String Culture;
	
	
	public JuridicalPersonNameTranslations(String JuridicalPersonNameJuridicalPersonIdIn, String LegalNameIn, String OperatingNameIn, String CultureIn) {
		JuridicalPersonNameJuridicalPersonId = JuridicalPersonNameJuridicalPersonIdIn;
		LegalName = LegalNameIn;
		OperatingName = OperatingNameIn;
		Culture = CultureIn;
		
		parameters = new String[]{"JuridicalPersonNameJuridicalPersonId", "LegalName", "OperatingName", "Culture"};
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
		String result = "('" + JuridicalPersonNameJuridicalPersonId + "'" + 
				", '" + LegalName + "'" +
				", '" + OperatingName + "'" +
				", '" + Culture + "')";
		
		return result;
	}
}
