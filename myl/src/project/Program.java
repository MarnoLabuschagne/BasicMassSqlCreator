package project;

import java.util.ArrayList;
import java.io.FileNotFoundException;

public class Program {
	public static void main(String[] args) {
		ToFileWriter fileWriter = new ToFileWriter();
		//Reads the text file
		ArrayList<String> listJP = new ArrayList<String>();
		ArrayList<String> listJPTypes = new ArrayList<String>();
		try {
			listJP = (args.length != 0) ? Functions.listFromFile(args[0], 50) : Functions.listFromFile("C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\docs\\input\\departments.txt", 100);
			listJPTypes = Functions.listFromFile("C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\docs\\input\\JPtypes.txt", 1);
			String file1Name = "JuridicalPersons";
			String file2Name = "JuridicalPersonNameTranslations";
			String prefix = "caselogixcms_dev.juridicalpersons.";
			ArrayList<JuridicalPersons> JPlist = Functions.makeJPList(listJP.size(), listJPTypes);
			ArrayList<JuridicalPersonNameTranslations> JPNTlist = Functions.makeJPNTList(listJP, JPlist);
			fileWriter.writeToFile(JPlist, prefix, file1Name, JPlist.get(0).getParameters());
			fileWriter.writeToFile(JPNTlist, prefix, file2Name, JPNTlist.get(0).getParameters());
		} catch (FileNotFoundException e) {
			System.out.println("Departments File not found.");
		}
		//
		
		ArrayList<String> listSFname = new ArrayList<String>();
		ArrayList<String> listSfdescription = new ArrayList<String>();
		try {
			listSFname = (args.length > 1) ? Functions.listFromFile(args[0], 50) : Functions.listFromFile("C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\docs\\input\\systemicNames.txt", 50);
			listSfdescription = (args.length > 2) ? Functions.listFromFile(args[1], 100) : Functions.listFromFile("C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\docs\\input\\systemicDescriptions.txt", 100);
			String file1Name = "SystemicFiles";
			String file2Name = "SystemicFileTranslations";
			String prefix = "caselogixcms_dev.systemicfiles.";
			ArrayList<SystemicFiles> SFlist = Functions.makeSFList(listSFname.size());
			ArrayList<SystemicFileTranslations> listSFT = Functions.makeSFTList(listSFname, listSfdescription, SFlist);
			fileWriter.writeToFile(SFlist, prefix, file1Name, SFlist.get(0).getParameters());
			fileWriter.writeToFile(listSFT, prefix, file2Name, listSFT.get(0).getParameters());
		} catch (FileNotFoundException e) {
			System.out.println("Systemics File not found.");
		}
	}
}
/*
natural persons address(not there yet) and phone number
*/