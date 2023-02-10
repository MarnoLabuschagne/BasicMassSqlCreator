package project;

import java.util.ArrayList;
import java.util.Collections;

public class Program {
	public static void main(String[] args) {
		String filePathRoot = "C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\";
		
		int choice = 9;
		switch (choice) {
		case 1:
			QueryMaker.juridicalPersons(null, null, filePathRoot);
			break;
		case 2: 
			QueryMaker.systemicFiles(50, 100, filePathRoot, null, null);
			break;
		case 3: //Parses a query and recreates it with the specified parameters
			ArrayList<String> parameters = new ArrayList<String>();
			Collections.addAll(parameters, "Id", "DisplayName", "Username", "Email", "Active", "Locked", "OrgUnits", "[Language]", "ModifiedOnUtc", "CreateOnUtc");
			QueryMaker.queryParser(parameters, "cya_users", filePathRoot);
			break;
		case 4:
			
			break;
		case 5:
			QueryMaker.populateDictionary(new String[] {"Names"}, "NamesDictionary", filePathRoot);
			break;
		case 6: //Adds new words to the dictionary. The Dictionary is used to create random strings
			QueryMaker.populateDictionary(new String[]{"systemicNames", "systemicDescriptions"}, "dictionary", filePathRoot);
			break;
		case 7: //Creates a large amount of systemicFile queries with random strings
			int numberOfValuesSF = 60;
			QueryMaker.queryRandomSystemic(numberOfValuesSF, 50, 100, "dictionary", filePathRoot);
			break;
		case 8://Creates a large amount of judicalPersons queries with random strings
			int numberOfValuesJP = 500;
			QueryMaker.queryRandomJuridicalPersons(numberOfValuesJP, 100, 1, "dictionary", filePathRoot);
			break;
		case 9:
			int numberOfValuesNP = 600;
			QueryMaker.queryRandomNaturalPersons(numberOfValuesNP, 50, 0, "NamesDictionary", filePathRoot);
		default:
			break;
		}
	}
}
/*
natural persons address(not there yet) and phone number
*/