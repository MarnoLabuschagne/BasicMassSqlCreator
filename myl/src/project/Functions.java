package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.UUID;

public class Functions {
	//Creates a random string.
	//leftLimit : smallest value characters
	//rightLimit : largest value characters
	//cap : size of the string
	public static String randomString(int leftLimit, int rightLimit, int cap) {
		Random random = new Random();
		String result = random.ints(leftLimit, rightLimit + 1)
				.filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
				.limit(cap)
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		return result;
	}
	
	//Reads a text file and returns an array list
		public static ArrayList<String> listFromFile(String filePath, int maxLength) throws FileNotFoundException{
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
				str = (str.length() > maxLength) ? str.substring(0, maxLength) : str;
				str = str.replace('\'', '`');
				result.add(str);
			}
			sc.close();
			return result;
		}
		/*
		 * if (str.charAt(0) >= 30 && str.charAt(0) <= 39 && maxLength == 1) {
					result.add(str.toCharArray()[0] + "");					
				} else {
					if (maxLength == 1) {
						str = str.substring(1, maxLength+1);
					}
					str = (str.length() > maxLength) ? str.substring(0, maxLength) : str;
					result.add(str);
				}
		 * */

		public static ArrayList<JuridicalPersons> makeJPList(int size, ) {
			ArrayList<JuridicalPersons> result = new ArrayList<JuridicalPersons>();
			for (int i=0; i<size; i++) {
				UUID uuid = UUID.randomUUID();
				result.add(new JuridicalPersons(uuid.toString(), Functions.randomString(48, 122, 40), true, true, 1));
				System.out.println(result.get(i));
			}
			return result;
		}

		public static ArrayList<JuridicalPersonNameTranslations> makeJPNTList(
				ArrayList<String> list,
				ArrayList<JuridicalPersons> jPlist) {
			
			ArrayList<JuridicalPersonNameTranslations> result = new ArrayList<JuridicalPersonNameTranslations>();
			for (int i=0; i<list.size(); i++) {
				result.add(new JuridicalPersonNameTranslations(jPlist.get(i).getId(), list.get(i), list.get(i), "en-ca"));
				System.out.println(result.get(i).toString());
			}
			return result;
		}

		public static ArrayList<SystemicFiles> makeSFList(int size) {
			ArrayList<SystemicFiles> result = new ArrayList<SystemicFiles>();
			for (int i=0; i<size; i++) {
				UUID uuid = UUID.randomUUID();
				UUID.randomUUID().toString();
				result.add(new SystemicFiles(uuid.toString(), UUID.randomUUID().toString(), Functions.randomString(48, 122, 40), false));
				System.out.println(result.get(i));
			}
			return result;
		}

		public static ArrayList<SystemicFileTranslations> makeSFTList(
				ArrayList<String> listN,
				ArrayList<String> listD,
				ArrayList<SystemicFiles> listSF) {
			ArrayList<SystemicFileTranslations> result = new ArrayList<SystemicFileTranslations>();
			try{
				for (int i=0; i<listN.size(); i++) {
					result.add(new SystemicFileTranslations(listSF.get(i).getId(), listN.get(i), listD.get(i), "en-ca"));
					System.out.println(result.get(i).toString());
				}
			} catch (NullPointerException e) {
				System.out.printf("Name list size[%d] not equal to description list size[%d]\n", listN.size(), listD.size());
			}
			return result;
		}
}
