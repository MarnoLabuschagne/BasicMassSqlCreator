package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import project.objects.JuridicalPersonNameTranslations;
import project.objects.JuridicalPersons;
import project.objects.NaturalPersons;
import project.objects.SystemicFileTranslations;
import project.objects.SystemicFiles;
import project.objects.query;

public class QueryMaker {

	public static int populateDictionary(String[] fileName, String dictionaryName, String root) {
		int results = 0;
		try {
			for (int i=0; i<fileName.length; i++) {
				results = randomString.populateDatabase(root + "docs\\input\\" + fileName[i] + ".txt", dictionaryName);
				System.out.printf("Added %d words from %s\n", results, fileName[i]);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find one of the following files: " + fileName + "\n\t [Function: populateDictionary]");
		}
		return results;
	}
	public static void queryRandomJuridicalPersons(int lines, int maxNameLength, int typeNum, String dictionaryName, String root) {
		Random random = new Random();
		ArrayList<String> list = new ArrayList<String>();
		ArrayList<String> listType = new ArrayList<String>();
		list = randomString.randomPhrases(5, lines, maxNameLength, dictionaryName);
		for (int i=0; i<lines; i++) {
			if (typeNum == 0) {
				listType.add("" + (random.nextInt(9)+1));
			} else {
				listType.add("" + 9);
			}
		}
		juridicalPersons(list, listType, root);
	}
	public static void queryRandomSystemic(int lines, int maxNameLength, int maxDescriptionLength, String dictionaryName, String root) {
		ArrayList<String> names = randomString.randomPhrases(5, lines, maxNameLength, dictionaryName);
		ArrayList<String> descriptions = randomString.randomPhrases(15, lines, maxDescriptionLength, dictionaryName);
		systemicFiles(maxNameLength, maxDescriptionLength, root, names, descriptions);
		 
	}
	public static void queryRandomNaturalPersons(int lines, int maxNameLength, int gender, String dictionaryName, String root) {
		ArrayList<String> names = randomString.randomPhrases(1, lines, maxNameLength, dictionaryName);
		ArrayList<String> surnames = randomString.randomPhrases(1, lines, maxNameLength, dictionaryName);
		ArrayList<String> genders = new ArrayList<String>();
		Random random = new Random();
		for (int i=0; i<lines; i++) {
			if (gender == 0) {
				genders.add("" + (random.nextInt(5)+1));
			} else {
				genders.add("" + 5);
			}
		}
		naturalPersons(maxNameLength, root, names, surnames, genders);
	}
	
	
	
	private static void naturalPersons(int maxNameLength, String root, ArrayList<String> listNPnamesIn, ArrayList<String> listNPsurnamesIn, ArrayList<String> listNPgendersIn) {
		ArrayList<String> listNPnames = listNPnamesIn;
		ArrayList<String> listNPsurname = listNPsurnamesIn;
		ArrayList<String> listNPgenders = listNPgendersIn;
		String fileName = "NaturalPersons";
		String prefix = "CaseLogixCMS_CYADEJ_testing2.naturalpersons";
		ArrayList<NaturalPersons> NPlist = Functions.makeNPList(listNPnames, listNPsurname, listNPgenders);
		ToFileWriter.writeToFileQuery(NPlist, prefix, fileName, NPlist.get(0).getParameters());
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
			String prefix = "CaseLogixCMS_CYADEJ_testing2.juridicalpersons";
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
			String prefix = "CaseLogixCMS_CYADEJ_testing2.systemicfiles";
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
			String prefix = "CaseLogixCMS_CYADEJ_testing2.useraccess";
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