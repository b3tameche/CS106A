import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

	public void init() {
		name = new JLabel("Name:");
		textField = new JTextField(20);
		Graph = new JButton("Graph");
		Clear = new JButton("clear");
		add(name, SOUTH);
		add(textField, SOUTH);
		add(Graph, SOUTH);
		add(Clear, SOUTH);
		
		graph = new NameSurferGraph();
		add(graph);
		
		addActionListeners();
	} 

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == Graph && textField.getText().length() >= 1) {
			String name = textField.getText();  
			name = name.toLowerCase(); 
			curr = dataBase.findEntry(name);
			
			if (curr != null) { 
				graph.addEntry(curr);
			}
			
			textField.setText("");
		}else if (e.getSource() == Clear) {
			graph.clear(); 
		}
	}
	
	private NameSurferGraph graph;
	private NameSurferDataBase dataBase = new NameSurferDataBase(NAMES_DATA_FILE); 
	private NameSurferEntry curr;
	
	private JButton Graph;
	private JButton Clear;
	private JTextField textField;
	private JLabel name;
}
