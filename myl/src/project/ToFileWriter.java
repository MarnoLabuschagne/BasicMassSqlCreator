package project;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import project.objects.query;

public class ToFileWriter {
	static String outputFilePath = "C:\\Users\\malabuschagne\\source\\javaThings\\BasicMassSqlCreator\\docs\\output";
	public static boolean writeToFile(ArrayList<?> list, String fileName) {
		File file = new File(outputFilePath + "\\" + fileName + ".txt");
		file.delete();
		try {
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			}
			FileWriter writer = new FileWriter(outputFilePath + "\\" + file.getName());
			for (int i=0; i<list.size(); i++) {
				writer.write(list.get(i).toString() + "\n");
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean writeToFileQuery(ArrayList<?> list, String prefix, String name, String param) {
		File file = new File(outputFilePath + "\\" + name + ".txt");
		file.delete();
		try {
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			  } else {
			    System.out.println("File already exists.");
			  }
			FileWriter writer = new FileWriter(outputFilePath + "\\" + file.getName());
			
			writer.write("INSERT INTO " +
					prefix + "." + name + "\n(" + 
					param + ")\n");
			writer.write("VALUES\n");
			
			for (int i=0; i<list.size(); i++) {
				if (i+1 == list.size()) {
					writer.write(list.get(i).toString() + ";");
				} else {
					writer.write(list.get(i).toString() + ",\n");
				}
			}
			writer.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Reads a text file and returns an array list
	public static ArrayList<String> listFromFile(String filePath, int maxLength, String ignoreMarker) throws FileNotFoundException{
		if (ignoreMarker == null) {
			ignoreMarker = "###";
		}
		if (filePath == null) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the text file's path:\n");
			filePath = scan.nextLine();
			scan.close();
		}
	
		ArrayList<String> result = new ArrayList<String>();
		File file = new File(filePath);
		System.out.println("File found: " + file.canRead());
		Scanner sc = new Scanner(file);
		while(sc.hasNext()){
			String str = sc.nextLine();
			str = (maxLength != 0 && str.length() > maxLength) ? str.substring(0, maxLength) : str;
			str = str.replace('\'', '`');
			if (str.length() > 0 && !str.startsWith(ignoreMarker)) {
				result.add(str);
			}
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
			
	public static ArrayList<query> parseQueries(String filePath) throws FileNotFoundException{
		if (filePath == null) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Please enter the text file's path:\n");
			filePath = scan.nextLine();
			scan.close();
		}
		
		ArrayList<query> result = new ArrayList<query>();
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		String[] str = new String[]{"", "", ""};
		while(sc.hasNext()){
			str[0] = sc.nextLine();
			if (str[0].length() > 11 && (str[0].substring(0, 11).equals("INSERT INTO") || str[0].substring(0, 11).equals("insert into"))) {
				str[0] = str[0].substring(12);
				System.out.println("0 : " + str[0]);
				str[1] = sc.nextLine();
				str[1] = str[1].substring(1, str[1].length()-1);
				System.out.println("1 :" + str[1]);
				str[2] = sc.nextLine();
				str[2] = str[2].substring(7, str[2].length()-1-1);
				System.out.println("2: " + str[2]);
				result.add(new query(str[0], str[1], str[2]));
			}
		}
		sc.close();
		return result;
	}
}
