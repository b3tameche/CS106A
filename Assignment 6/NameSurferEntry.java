import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

	public NameSurferEntry(String line) {
		forString = line; 
		List<String> entry = Arrays.asList(line.split(" ")); 
		name = entry.get(0); 
		positions = new ArrayList<>();
		for(int i = 1; i < entry.size(); i++) {
			positions.add(Integer.parseInt(entry.get(i)));
		}
	}

	public String getName() {
		return name;
	}

	public int getRank(int decade) {
		return positions.get(decade);
	}

	public String toString() {
		return forString;
	}

	public List<Integer> getPositions(){
		return positions;
	}
	
	private String name;
	private List<Integer> positions;
	private String forString;
}

