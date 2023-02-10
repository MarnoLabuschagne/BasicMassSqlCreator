package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class randomString {
	private static String dictionaryFilePath = "C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\docs\\";
	
	public static int populateDatabase(String filePath, String fileName) throws FileNotFoundException {
		int numWordsAdded = 0;
		if (filePath == null) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the text file's path:\n");
			filePath = scan.nextLine();
			scan.close();
		}
	
		ArrayList<String> dictionary = new ArrayList<String>();
		try {
			File fileOld = new File(dictionaryFilePath + fileName + ".txt");
			System.out.println("Dictionary found: " + fileOld.canRead());
			Scanner sc = new Scanner(fileOld);
			while(sc.hasNext()){
				dictionary.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("Could not find Dictionary.\n\tCreating Dictionary");
			File file = new File(dictionaryFilePath + fileName + ".txt");
			try {
				file.createNewFile();
			} catch (IOException e1) {
				System.out.println("\tCould Not Create Dictionary");
				return 0;
			}
		}
		
		
		File file = new File(filePath);
		System.out.println("File found: " + file.canRead());
		Scanner sc = new Scanner(file);
		while(sc.hasNext()) {
			String str = sc.nextLine();
			str = str.replace("\"", " ");
			str = str.replace(".", " ");
			str = str.replace(",", " ");
			str = str.replace("!", " ");
			str = str.replace("?", " ");
			str = str.replace("(", " ");
			str = str.replace(")", " ");
			str = str.replace("“", " ");
			str = str.replace(":", " ");
			str = str.replace(";", " ");
			Scanner scan = new Scanner(str);
			scan.useDelimiter(" ");
			while (scan.hasNext()) {
				String word = scan.next();
				word = word.replace('\'', '`');
				if(word.length() > 1 || word.equals("a") || word.equals("I")) { dictionary.add(word);}
				numWordsAdded++;
			}
			scan.close();
		}
		sc.close();
		try {
			FileWriter writer = new FileWriter(dictionaryFilePath + fileName + ".txt");
			for(int i=0; i<dictionary.size(); i++) {
				writer.write(dictionary.get(i) + "\n");
			}
			writer.close();
		} catch (IOException e) {
			System.out.println("Could Not Create Dictionary Or Could Not Write To It");
		}
		return numWordsAdded;
	}
	
	public static ArrayList<String> randomPhrases(int wordCount, int phraseCount, int maxChar, String fileName) {
		ArrayList<String> result = new ArrayList<String>();
		try {
			ArrayList<String> dictionary = ToFileWriter.listFromFile(dictionaryFilePath + fileName + ".txt", 25, null);
			Collections.shuffle(dictionary);
			int progress = 0;
			for (int i=0; i<phraseCount; i++) {
				String phrase = "";
				for (int j=0; j<dictionary.size() && j<wordCount; j++) {
					if (progress+1 == dictionary.size()) {
						Collections.shuffle(dictionary);
						progress = 0;
					}
					String word = dictionary.get(progress++);
					phrase += word + " ";
				}
				if (maxChar != 0) {
					System.out.println("\t" + phrase);
					phrase = (maxChar != 0 && phrase.length() > maxChar) ? phrase.substring(0, maxChar) : phrase;
				}
				result.add(phrase);
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Could not find Dictionary");
			return null;
		}
		return result;
	}
	
	public static String randomPhrase(int wordCount, String fileName) {
		String result = "";
		try {
			ArrayList<String> dictionary = ToFileWriter.listFromFile(dictionaryFilePath + fileName + ".txt", 25, null);
			Collections.shuffle(dictionary);
			for (int i=0; i<dictionary.size() && i<wordCount; i++) {
				result += dictionary.get(i) + " ";
				// Uncomment this at your own risk
				//System.out.println("\t\t" + dictionary.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Could not find Dictionary");
			return null;
		}
		return result;
	}
}
