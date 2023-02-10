package project;

import java.util.ArrayList;
import java.util.Scanner;

public class query {
	private String insertInto;
	private ArrayList<Values> values;
	
	public query(String insertInto, ArrayList<Values> values) {
		this.insertInto = insertInto;
		this.values = values;
	}
	
	public query(String insertInto, String parameters, String values) {
		this.insertInto = insertInto;
		Scanner scan = new Scanner(parameters).useDelimiter(", ");
		ArrayList<String> PARAMETERS = new ArrayList<String>();
		while (scan.hasNext()) {
			PARAMETERS.add(scan.next());
		}
		scan.close();
		scan = new Scanner(values).useDelimiter(", ");
		ArrayList<String> VALUES = new ArrayList<String>();
		while (scan.hasNext()) {
			VALUES.add(scan.next());
		}
		scan.close();
		
		this.values = new ArrayList<Values>();
		for(int i=0; i<PARAMETERS.size(); i++) {
			this.values.add(new Values(PARAMETERS.get(i), VALUES.get(i)));
		}
	}

	public void setInsertInto(String string) {
		insertInto = string;		
	}
	
	public String toString() {
		String result = "";
		
		result += "INSERT INTO " + insertInto + "\n";
		boolean swtch = false;
		for (int i=0; i<values.size(); i++) {
			if (i==0) {
				result += "(";
			}
			if (!swtch) {
				result += values.get(i).getParameter();
				if (i+1!= values.size()) {
					result += ", ";
				} else {
					result += ")\nVALUES";
					swtch = true;
					i = -1;
				}
			} else {
				result += values.get(i).getValue();
				if (i+1!= values.size()) {
					result += ", ";
				} else {
					result += ");";
				}
			}
		}
		return result;
	}
	
	public String toString(ArrayList<String> whiteList) {
		String result = "";
		
		result += "INSERT INTO " + insertInto + "\n";
		boolean swtch = false;
		boolean valid = false;
		boolean comma = false;
		for (int i=0; i<values.size(); i++) {
			if (i==0) {
				result += "(";
			}
			for (int j=0; j<whiteList.size(); j++) {
				if (values.get(i).getParameter().equals(whiteList.get(j))) {
					valid = true;
					break;
				}
			}
			if (valid) {
				if (comma) {
					result += ", ";
					comma = false;
				}
				if (!swtch) {
					result += values.get(i).getParameter();
				} else {
					result += values.get(i).getValue();
				}
				comma = true;
				valid = false;
			}
			
			if (!swtch && i+1 == values.size()){
				result += ")\nVALUES";
				swtch = true;
				comma = false;
				i = -1;
			} else if (swtch && i+1 == values.size()){
				result += ");";
			}
		}
		return result;
	}

}
