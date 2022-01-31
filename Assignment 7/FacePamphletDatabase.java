import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	public FacePamphletDatabase() {
		database = new HashMap<>();
	}
	
	public void addProfile(FacePamphletProfile profile) {
		database.put(profile.getName(), profile);
	}

	public FacePamphletProfile getProfile(String name) {
		if (database.keySet().contains(name)) {
			return database.get(name);
		}
		return null;
	}
	
	public void deleteProfile(String name) {
		if (database.keySet().contains(name)) {
			Iterator i = database.get(name).getFriends();
			while (i.hasNext()) {
					database.get(i.next()).removeFriend(name);
			}
			database.remove(name);
		}
	}

	public boolean containsProfile(String name) {
		if (database.keySet().contains(name)) {
			return true;
		}
		return false;
	}
	
	private Map<String, FacePamphletProfile> database;
}
