import acm.graphics.*;
import java.util.*;

public class FacePamphletProfile implements FacePamphletConstants {
	
	public FacePamphletProfile(String name) {
		Name = name;
		Status = null;
		FriendList = new ArrayList<>();
		ProfPic = null;
	}

	public String getName() {
		return Name;
	}

	public GImage getImage() {
		return ProfPic;
	}

	public void setImage(GImage image) {
		ProfPic = image;
	}

	public String getStatus() {
		if (Status != null) {
			return Status;
		}
		return "";
	}
	
	public void setStatus(String status) {
		Status = status;
	}

	public boolean addFriend(String friend) {
		if (!FriendList.contains(friend)) {
			FriendList.add(friend);
			return true;
		}
		return false;
	}

	public boolean removeFriend(String friend) {
		if (FriendList.contains(friend)) {
			FriendList.remove(friend);
			return true;
		}
		return false;
	}

	public Iterator<String> getFriends() {
		friendsIterator = FriendList.iterator();
		return friendsIterator;
	}
	
	public String toString() {
		String info = Status == null ? Name + " () :" : Name + " (" + Status + ") : "; // assigns different string values depending on the status
		Iterator<String> i = FriendList.iterator();
		while(i.hasNext()) {
			info += i.next() + ", ";
		}
		
		return info;
	}
	
	private String Name;
	private String Status;
	private GImage ProfPic;
	private List<String> FriendList;
	private Iterator<String> friendsIterator;
}
