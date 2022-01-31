import java.io.*;
import java.util.*;

public class NameSurferDataBase implements NameSurferConstants {
	
	public NameSurferDataBase(String filename) {		
		names = new HashMap<>(); 
		try {
			BufferedReader rd = new BufferedReader(new FileReader(filename));
			while (true) {
				String line = rd.readLine();
				if (line == null) break;
				NameSurferEntry entry = new NameSurferEntry(line);
				names.put(entry.getName().toLowerCase(), entry); // stores names in lowercase
			}
			rd.close();
		} catch (Exception e){
			System.out.println("File not found or error occurs as the file is being read.");
		}
	}

	public NameSurferEntry findEntry(String name) {
		if (names.keySet().contains(name)) {
			NameSurferEntry curr = names.get(name);
			return curr;
		}
		return null;
	}
	
	private Map<String, NameSurferEntry> names;
}

