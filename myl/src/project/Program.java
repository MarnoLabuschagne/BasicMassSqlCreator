package project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Program {
	public static void main(String[] args) {
		String filePathRoot = "C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\";
		
		int choice = 5;
		switch (choice) {
		case 1:
			juridicalPersons(null, null, filePathRoot);
			break;
		case 2: 
			systemicFiles(50, 100, filePathRoot, null, null);
			break;
		case 3: //Parses a query and recreates it with the specified parameters
			ArrayList<String> parameters = new ArrayList<String>();
			Collections.addAll(parameters, "Id", "DisplayName", "Username", "Email", "Active", "Locked", "OrgUnits", "[Language]", "ModifiedOnUtc", "CreateOnUtc");
			queryParser(parameters, "cya_users", filePathRoot);
			break;
		case 4: //Adds new words to the dictionary. The Dictionary is used to create random strings
			populateDictionary(new String[]{"systemicNames", "systemicDescriptions"}, filePathRoot);
			break;
		case 5: //Creates a large amount of systemicFile queries with random strings
			int numberOfValuesSF = 400;
			queryRandomSystemic(numberOfValuesSF, 50, 100, filePathRoot);
			break;
		case 6://Creates a large amount of judicalPersons queries with random strings
			int numberOfValuesJP = 400;
			queryRandomJuridicalPersons(numberOfValuesJP, 100, 0, filePathRoot);
			break;
		default:
			break;
		}
	}
	
	public static void queryRandomJuridicalPersons(int lines, int maxNameLength, int typeNum, String root) {
		Random random = new Random();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listType = new ArrayList<String>();
		list = randomString.randomPhrases(5, lines, maxNameLength);
		for (int i=0; i<lines; i++) {
			if (typeNum == 0) {
				listType.add("" + (random.nextInt(9)+1));
			} else {
				listType.add("" + 9);
			}
		}
		juridicalPersons(list, listType, root);
	}
	
	public static void queryRandomSystemic(int lines, int maxNameLength, int maxDescriptionLength, String root) {
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> descriptions = new ArrayList<String>();
		names = randomString.randomPhrases(5, lines, maxNameLength);
		descriptions = randomString.randomPhrases(15, lines, maxDescriptionLength);
		systemicFiles(50, 100, root, names, descriptions);
		 
	}
	
	public static int populateDictionary(String[] fileName, String root) {
		int results = 0;
		try {
			for (int i=0; i<fileName.length; i++) {
				results = randomString.populateDatabase(root + "docs\\input\\" + fileName[i] + ".txt");
				System.out.printf("Added %d words from %s\n", results, fileName[i]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find one of the following files: " + fileName + "\n\t [Function: populateDictionary]");
		}
		return results;
	}
	
	public static void juridicalPersons(ArrayList<String> listJPIn, ArrayList<String> listJPTypesIn, String root) {
		int defaultType = 9;
		try {
			ArrayList<Integer> listJPTypes = new ArrayList<Integer>();
			ArrayList<String> listJP = (listJPIn != null) ? listJPIn :  ToFileWriter.listFromFile(root + "docs\\input\\departments.txt", 100, "#");
			try {
				ArrayList<String> listJPTypesTemp = (listJPTypesIn != null) ? listJPTypesIn : ToFileWriter.listFromFile(root + "docs\\input\\departmentTypes.txt", 2, "#");
				for (int i=0; i<listJP.size(); i++) {
					if (i >= listJPTypesTemp.size()) {
						listJPTypes.add(defaultType);
					} else {
						listJPTypes.add(Integer.parseInt(listJPTypesTemp.get(i)));
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Type file not found. Using default");
				for (int i=0; i<listJP.size(); i++) {
					listJPTypes.add(defaultType);				
				}
			}				
			String file1Name = "JuridicalPersons";
			String file2Name = "JuridicalPersonNameTranslations";
			String prefix = "caselogixcms_dev.juridicalpersons.";
			ArrayList<JuridicalPersons> JPlist = Functions.makeJPList(listJP.size(), listJPTypes);
			ArrayList<JuridicalPersonNameTranslations> JPNTlist = Functions.makeJPNTList(listJP, JPlist);
			ToFileWriter.writeToFileQuery(JPlist, prefix, file1Name, JPlist.get(0).getParameters());
			ToFileWriter.writeToFileQuery(JPNTlist, prefix, file2Name, JPNTlist.get(0).getParameters());
		} catch (FileNotFoundException e) {
			System.out.println("Departments File not found.");
		}
	}
	
	public static void systemicFiles(int maxNameLength, int maxDescriptionLength, String root, ArrayList<String> listSFnameIn, ArrayList<String> listSFdescriptionIn) {
		ArrayList<String> listSFname = (listSFnameIn != null) ? listSFnameIn : new ArrayList<String>();
		ArrayList<String> listSfdescription = (listSFdescriptionIn != null) ? listSFdescriptionIn : new ArrayList<String>();
		try {
			listSFname = (listSFname.size() > 0) ? listSFname : ToFileWriter.listFromFile(root + "docs\\input\\systemicNames.txt", maxNameLength, "Meaning:");
			listSfdescription = (listSfdescription.size() > 0) ? listSfdescription : ToFileWriter.listFromFile(root + "docs\\input\\systemicDescriptions.txt", maxDescriptionLength, null);
			String file1Name = "SystemicFiles";
			String file2Name = "SystemicFileTranslations";
			String prefix = "caselogixcms_dev.systemicfiles.";
			ArrayList<SystemicFiles> SFlist = Functions.makeSFList(listSFname.size());
			ArrayList<SystemicFileTranslations> listSFT = Functions.makeSFTList(listSFname, listSfdescription, SFlist);
			ToFileWriter.writeToFileQuery(SFlist, prefix, file1Name, SFlist.get(0).getParameters());
			ToFileWriter.writeToFileQuery(listSFT, prefix, file2Name, listSFT.get(0).getParameters());
		} catch (FileNotFoundException e) {
			System.out.println("Systemics File not found.");
		}
	}
	
	public static void queryParser(ArrayList<String> parameters, String fileNameIn, String root) {
		try {
			String fileName = fileNameIn;
			String prefix = "caselogixcms_dev.useraccess.";
			ArrayList<String> whiteList = parameters;
			
			ArrayList<query> queryList = ToFileWriter.parseQueries(root + "docs\\input\\" + fileName + ".txt");
			File file = new File(root + "docs\\output\\" + fileName + ".txt");
			file.delete();
			FileWriter writer = new FileWriter(root + "docs\\output\\" + fileName + ".txt");
			System.out.println("File created: " + file.getName());
			for (int i=0; i<queryList.size(); i++) {
				queryList.get(i).setInsertInto(prefix+""+fileName);
				writer.write(queryList.get(i).toString(whiteList) + "\n");
			}
			writer.close();
		} catch (FileNotFoundException e) {
			System.out.println("Query File not found.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
/*
natural persons address(not there yet) and phone number
*/