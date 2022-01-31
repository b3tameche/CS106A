import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program implements FacePamphletConstants {

	public void init() {
		database = new FacePamphletDatabase();
		canvas = new FacePamphletCanvas();
		canvas.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
		add(canvas);
		
		add(new JLabel("Name:"), NORTH);
		nameField = new JTextField(TEXT_FIELD_SIZE);
		add(nameField, NORTH);
		add(new JButton("Add"), NORTH);
		add(new JButton("Delete"), NORTH);
		add(new JButton("Lookup"), NORTH);
		
		statusField = new JTextField(TEXT_FIELD_SIZE);
		statusField.addActionListener(this);
		add(statusField, WEST);
		add(new JButton("Change Status"), WEST);
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		pictureField = new JTextField(TEXT_FIELD_SIZE);
		pictureField.addActionListener(this);
		add(pictureField, WEST);
		add(new JButton("Change Picture"), WEST);		
		add(new JLabel(EMPTY_LABEL_TEXT), WEST);
		
		friendField = new JTextField(TEXT_FIELD_SIZE);
		friendField.addActionListener(this);
		add(friendField, WEST);
		add(new JButton("Add Friend"), WEST);
		
		addActionListeners();
    }
    
    public void actionPerformed(ActionEvent e) {
    	String name = nameField.getText(); 
    	
		if (e.getActionCommand() == "Add" && !name.equals("")) {  
			if (!database.containsProfile(name)) { 				  
				currentProfile = new FacePamphletProfile(name);   
				database.addProfile(currentProfile); 			  
				canvas.displayProfile(currentProfile);  		  
				canvas.showMessage("New profile created");
			} else {
				currentProfile = database.getProfile(name);  	   
				canvas.displayProfile(currentProfile); 			  
				canvas.showMessage("The profile with the name " + name + " already exists"); 
			}
		}

		if(e.getActionCommand() == "Delete" && !name.equals("")) {
			if (database.containsProfile(name)) {
				database.deleteProfile(name); 
				currentProfile = null;
				canvas.displayProfile(currentProfile);
				canvas.showMessage("The profile " + name + " successfully deleted");
			}else {
				canvas.showMessage("The Profile with the name " + name + " does not exist");
			}
		}
		
		if (e.getActionCommand() == "Lookup" && !name.equals("")) {
			if (database.containsProfile(name)) {
				currentProfile = database.getProfile(name);
				canvas.displayProfile(currentProfile);
				canvas.showMessage("Displaying " + name);
			} else {
				currentProfile = null;
				canvas.displayProfile(currentProfile);
				canvas.showMessage("The profile " + name + " does not exist");
			}
		}
		
		if ((e.getSource() == statusField || e.getActionCommand() == "Change Status") && !statusField.getText().equals("")) { 
			if (currentProfile != null) { 
				String updatedStatus = statusField.getText(); 
				currentProfile.setStatus(updatedStatus);  
				canvas.displayProfile(currentProfile); 
				canvas.showMessage("Status updated to " + updatedStatus); 
			} else {
				canvas.showMessage("Please select a profile to change status"); 
			}
		}
		
		if ((e.getSource() == pictureField || e.getActionCommand() == "Change Picture") && !pictureField.getText().equals("")) {
			if (currentProfile != null) { 
				GImage image = null; 
				try { 
					image = new GImage(pictureField.getText()); 
					currentProfile.setImage(image); 
					canvas.displayProfile(currentProfile);
					canvas.showMessage("Picture updated");
				} catch (ErrorException ex) { 
					canvas.showMessage("Unable to open image file: " + pictureField.getText());
				}
			} else {
				canvas.showMessage("Please select a profile to change picture");
			}
		}
		
		if ((e.getSource() == friendField || e.getActionCommand() == "Add Friend") && !friendField.getText().equals("")) {
			if (currentProfile != null) { 
				if (!friendField.getText().equals(currentProfile.getName())) { 
					if (database.containsProfile(friendField.getText())) { 
						if (currentProfile.addFriend(friendField.getText())) {
							database.getProfile(friendField.getText()).addFriend(currentProfile.getName()); 
							canvas.displayProfile(currentProfile); 
							canvas.showMessage(friendField.getText() + " added as a friend");
						} else {
							canvas.showMessage(currentProfile.getName() + " already has " + friendField.getText() + " as a friend");
						}
					} else {
						canvas.showMessage(friendField.getText() + " doesn't exist");
					}					
				} else {
					canvas.showMessage("A profile can't be a friend of himself");
				}
			} else {
				canvas.showMessage("Please select a profile to add friend");
			}
		}
	}
    
    private JTextField nameField;
    private JTextField statusField;
    private JTextField pictureField;
    private JTextField friendField;
    
    private FacePamphletDatabase database;
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;
}
