import acm.graphics.*;
import java.awt.*;
import java.util.*;

public class FacePamphletCanvas extends GCanvas implements FacePamphletConstants {

	public FacePamphletCanvas() {
		message = null;
	}
	
	public void showMessage(String msg) {
		if (message != null) { 
			remove(message);
		}
		message = new GLabel(msg);
		message.setFont(MESSAGE_FONT);
		add(message, (getWidth() - message.getWidth()) / 2, getHeight() - BOTTOM_MESSAGE_MARGIN);

	}

	public void displayProfile(FacePamphletProfile profile) {
		removeAll();
		
		if (profile != null) { 
			GLabel Name = new GLabel(profile.getName()); 
			Name.setFont(PROFILE_NAME_FONT);
			Name.setColor(Color.blue);
			add(Name, LEFT_MARGIN, 2*TOP_MARGIN);
			
			if (profile.getImage() == null) { 
				GRect imageRect = new GRect(IMAGE_WIDTH, IMAGE_HEIGHT);
				GLabel noImage = new GLabel("No Image");
				noImage.setFont(PROFILE_IMAGE_FONT);
				add(imageRect, LEFT_MARGIN, Name.getY() + IMAGE_MARGIN);
				add(noImage, LEFT_MARGIN + IMAGE_WIDTH/2 - noImage.getWidth()/2, Name.getY() + IMAGE_MARGIN + IMAGE_HEIGHT/2 + Name.getAscent()/2);
			} else {
				image = profile.getImage();
				image.scale(IMAGE_WIDTH/image.getWidth(), IMAGE_HEIGHT/image.getHeight());
				add(image, LEFT_MARGIN, Name.getY() + IMAGE_MARGIN);
			}
			
			if (profile.getStatus() != "") { 
				status = new GLabel(profile.getStatus());
				status.setFont(PROFILE_STATUS_FONT);
				add(status, LEFT_MARGIN, Name.getY() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
			} else {
				status = new GLabel("No current status");
				status.setFont(PROFILE_STATUS_FONT);
				add(status, LEFT_MARGIN, Name.getY() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
			}
			
			GLabel friends = new GLabel("Friends: "); 
			friends.setFont(PROFILE_FRIEND_LABEL_FONT);
			add(friends, getWidth()/2, Name.getY() + IMAGE_MARGIN);
				
			double gap = 1;
			Iterator<String> i = profile.getFriends();
			while (i.hasNext()) {
				GLabel currentFriend = new GLabel(i.next());
				currentFriend.setFont(PROFILE_FRIEND_FONT);
				add(currentFriend, getWidth()/2, Name.getY() + IMAGE_MARGIN + currentFriend.getAscent()*gap);
				gap++;
			}
		}
	}

	private GLabel message;
	private GLabel status;
	private GImage image;
}
