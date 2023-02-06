package project;
import java.util.ArrayList;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.UUID;

public class Program {
	public static void main(String[] args) {
		//Reads the text file
		ArrayList<String> list = new ArrayList<String>();
		try {
			list = (args.length != 0) ? listFromFile(args[0]) : listFromFile("C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\myl\\src\\project\\departments.txt");
		} catch (FileNotFoundException e) {
			System.out.println("File path not found.");
			System.exit(0);
		}
		
		//Creates 1 array of an object
		ArrayList<JuridicalPersons> JPlist = new ArrayList<JuridicalPersons>();
		for (int i=0; i<list.size(); i++) {
			UUID uuid = UUID.randomUUID();
			JPlist.add(new JuridicalPersons(uuid.toString(), randomString(48, 122, 40), true, true, 1));
			System.out.println(JPlist.get(i));
		}
		
		//Creates another array of an object
		ArrayList<JuridicalPersonNameTranslations> JPNTlist = new ArrayList<JuridicalPersonNameTranslations>();
		for (int i=0; i<list.size(); i++) {
			JPNTlist.add(new JuridicalPersonNameTranslations(JPlist.get(i).getId(), list.get(i), list.get(i), "en-ca"));
			System.out.println(JPNTlist.get(i).toString());
		}
		
		//Prints to text files
		ToFileWriter fileWriter = new ToFileWriter();
		fileWriter.writeToFile(JPlist, "caselogixcms_dev.juridicalpersons.JuridicalPersons", "Id, ResourceIdentifier, IsValidRespondent, IsActive, JuridicalPersonTypeId");
		fileWriter.writeToFile(JPNTlist, "caselogixcms_dev.juridicalpersons.JuridicalPersonNameTranslations", "JuridicalPersonNameJuridicalPersonId, LegalName, OperatingName, Culture");
	}
	
	//Creates a random string.
	//leftLimit : smallest value characters
	//rightLimit : largest value characters
	//cap : size of the string
	private static String randomString(int leftLimit, int rightLimit, int cap) {
		Random random = new Random();
		String result = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(cap)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		return result;
	}
	
	//Reads a text file and returns an array list
	public static ArrayList<String> listFromFile(String filePath) throws FileNotFoundException{
		if (filePath == null) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the text file's path:\n");
			filePath = scan.nextLine();
			scan.close();
		}
		ArrayList<String> result = new ArrayList<String>();
		
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		
		while(sc.hasNext()){
			String str = sc.nextLine();
			result.add(str);
			System.out.println(str);
		}
		sc.close();
		return result;
	}
}
