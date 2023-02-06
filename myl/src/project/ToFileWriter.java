package project;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ToFileWriter {
	public boolean writeToFile(ArrayList<?> list, String name, String param) {
		File file = new File(name + ".txt");
		file.delete();
		try {
			if (file.createNewFile()) {
				System.out.println("File created: " + file.getName());
			  } else {
			    System.out.println("File already exists.");
			  }
			FileWriter writer = new FileWriter(file.getName());
			
			writer.write("INSERT INTO " +
					name + " (" + 
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
}
