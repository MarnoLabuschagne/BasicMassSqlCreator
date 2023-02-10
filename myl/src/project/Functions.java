package project;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import project.objects.JuridicalPersonNameTranslations;
import project.objects.JuridicalPersons;
import project.objects.NaturalPersons;
import project.objects.SystemicFileTranslations;
import project.objects.SystemicFiles;

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
	public static String randomPhoneNumber(String prefix) {
		Random random = new Random();
		String result = "";
		int  cycles = 10;
		if (prefix != null) {
			result += prefix;
			cycles += -3;
		}
		
		for (int i=0; i<cycles; i++) {
			result += "" + (random.nextInt(9)+1);
		}
		return result;
	}
	
	public static ArrayList<JuridicalPersons> makeJPList(int size, ArrayList<Integer> types) {
		ArrayList<JuridicalPersons> result = new ArrayList<JuridicalPersons>();
		for (int i=0; i<size; i++) {
			UUID uuid = UUID.randomUUID();
			result.add(new JuridicalPersons(uuid.toString(), Functions.randomString(48, 122, 40), true, true, types.get(i)));
			//System.out.println(result.get(i));
		}
		return result;
	}
	public static ArrayList<JuridicalPersonNameTranslations> makeJPNTList(
			ArrayList<String> list,
			ArrayList<JuridicalPersons> jPlist) {
		ArrayList<JuridicalPersonNameTranslations> result = new ArrayList<JuridicalPersonNameTranslations>();
		for (int i=0; i<list.size(); i++) {
			result.add(new JuridicalPersonNameTranslations(jPlist.get(i).getId(), list.get(i), list.get(i), "en-ca"));
			//System.out.println(result.get(i).toString());
		}
		return result;
	}
	public static ArrayList<SystemicFiles> makeSFList(int size) {
		ArrayList<SystemicFiles> result = new ArrayList<SystemicFiles>();
		for (int i=0; i<size; i++) {
			UUID uuid = UUID.randomUUID();
			result.add(new SystemicFiles(uuid.toString(), UUID.randomUUID().toString(), Functions.randomString(48, 122, 40), false));
			//System.out.println(result.get(i));
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
	public static ArrayList<NaturalPersons> makeNPList(
			ArrayList<String> listNPnames, 
			ArrayList<String> listNPsurname,
			ArrayList<String> listNPgenders) {
		ArrayList<NaturalPersons> result = new ArrayList<NaturalPersons>();
		try {
			for (int i=0; i<listNPnames.size(); i++) {
				UUID uuid = UUID.randomUUID();
				result.add(new NaturalPersons(uuid.toString(), Functions.randomString(48, 122, 40), listNPnames.get(i), listNPsurname.get(i), listNPgenders.get(i)));
				System.out.println(result.get(i).toString());
			}
		} catch (NullPointerException e) {
			System.out.printf("Name list size[%d], surname list size [%d}, and gender list size[%d] are not equal\n", listNPnames.size(), listNPsurname.size(), listNPgenders.size());
		}
		return result;
	}
	
}
