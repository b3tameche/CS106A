import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;
import java.util.List;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	public NameSurferGraph() {
		addComponentListener(this);
	}
	
	public void clear() {
		entries.clear();
		update();
	}
	
	public void addEntry(NameSurferEntry entry) {
		entries.add(entry);
		update();
	}
	
	public void update() {
		removeAll();
		drawGraph();
	}
	
	private void drawGraph() {
		drawLines();
		
		for (NameSurferEntry entry : entries) { 
			tempRanks = entry.getPositions(); 
 
			double startingY = tempRanks.get(0) != 0 ? GRAPH_MARGIN_SIZE + tempRanks.get(0) * (getHeight() - 2*GRAPH_MARGIN_SIZE)/MAX_RANK : getHeight() - GRAPH_MARGIN_SIZE;
			double endingY = 0; 
			int remainder = entries.indexOf(entry) % 4; 
			for(int i = 0; i < NDECADES; i++) {  
				if (i!=NDECADES-1) { 
					endingY = tempRanks.get(i+1) != 0 ? GRAPH_MARGIN_SIZE + tempRanks.get(i+1)*(getHeight() - 2*GRAPH_MARGIN_SIZE)/MAX_RANK : getHeight() - GRAPH_MARGIN_SIZE; 
					add(new GLine(i*( (int) getWidth()/NDECADES), startingY, (i+1)*( (int) getWidth()/NDECADES), endingY)); 
					this.getElementAt(i*( (int) getWidth()/NDECADES), startingY).setColor(colorizer(remainder)); 
				}
				 
				add(new GLabel(entry.getName() + " " + (tempRanks.get(i) == 0 ? "*" : tempRanks.get(i))), i*( (int) getWidth()/NDECADES) + 2, startingY - 2);
				this.getElementAt(i*( (int) getWidth()/NDECADES) + 2, startingY - 2).setColor(colorizer(remainder)); 
				startingY = endingY; 
			}
		}
	}
	
	private void drawLines() { 
		for(int i = 0; i < NDECADES; i++) {
			add(new GLine(i*(getWidth()/NDECADES), 0, i*(getWidth()/NDECADES), getHeight()));
			add(new GLabel("" + (START_DECADE+i*10)), i*( (int) getWidth()/NDECADES) + 2, getHeight() - 4);
		}
		add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
		add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
	}
	
	private Color colorizer(int remainder) { 
		switch (remainder) {
			case 0 : return Color.black;
			case 1 : return Color.red;
			case 2 : return Color.blue;
			case 3 : return Color.magenta;
			default : break;
		}
		return null;
	}
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
	
	private ArrayList<NameSurferEntry> entries = new ArrayList<>();
	private List<Integer> tempRanks;
}
