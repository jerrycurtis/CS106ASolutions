/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {
	
	private ArrayList<NameSurferEntry> graphentries = new ArrayList<>();
	public static final int LABEL_OFFSET = 5;

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
		update();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
		graphentries.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
		graphentries.add(entry);	
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
		removeAll();
		drawGrid();
		drawBottomLabels();
		drawEntries();
	}
	
    private void drawEntries() {
        if(graphentries.size() >= 0) {
            for(int i = 0; i < graphentries.size(); i++) {
                NameSurferEntry entries = graphentries.get(i);
                drawEntry(entries, i);
            }
        }
    }
	
    private void drawEntry(NameSurferEntry entry, int entryColor) {
        for(int i = 0; i < NDECADES - 1; i++) {
            int rank1 = entry.getRank(i);
            int rank2 = entry.getRank(i+1);
            double x1 = i * (getWidth()/NDECADES);
            double x2 = (i+1) * (getWidth()/NDECADES);
            double y1 = 0;
            double y2 = 0;
            if(rank1 != 0 && rank2 != 0) {
                y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank1/MAX_RANK;
                y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank2/MAX_RANK;
            }
            else if(rank1 == 0 && rank2 == 0) {
                y1 = getHeight() - GRAPH_MARGIN_SIZE;
                y2 = getHeight() - GRAPH_MARGIN_SIZE;
            }
            else if (rank1 == 0){
                y1 = getHeight() - GRAPH_MARGIN_SIZE;
                y2 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank2/MAX_RANK;
            }
            else if(rank2 == 0) {
                y1 = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank1/MAX_RANK;
                y2 = getHeight() - GRAPH_MARGIN_SIZE;
            }
            GLine line = new GLine(x1, y1, x2, y2);
            
            if(entryColor%4 == 1) {
                line.setColor(Color.RED);
            }
            else if(entryColor%4 == 2) {
                line.setColor(Color.BLUE);
            }
            else if(entryColor%4 == 3) {
                line.setColor(Color.MAGENTA);
            }
            add(line);
        }
        
        for(int i = 0; i<NDECADES; i++) {
            String name = entry.getName();
            int rank = entry.getRank(i);
            String label = name + " " + rank;
            double x = i * (getWidth()/NDECADES) + LABEL_OFFSET;
            double y = 0;
            if(rank != 0) {
                y = GRAPH_MARGIN_SIZE + (getHeight() - GRAPH_MARGIN_SIZE*2) * rank/MAX_RANK - LABEL_OFFSET;
            }
            else{
                label = name + " *";
                y = getHeight() - GRAPH_MARGIN_SIZE - LABEL_OFFSET;
            }
            GLabel nameLabel = new GLabel(label, x, y);
            
            if(entryColor%4 == 1) {
                nameLabel.setColor(Color.RED);
            }
            else if(entryColor%4 == 2) {
                nameLabel.setColor(Color.BLUE);
            }
            else if(entryColor%4 == 3) {
                nameLabel.setColor(Color.MAGENTA);
            }
            add(nameLabel);
        }
    }
    
	private void drawGrid() {
		int x = getWidth();
		int y = getHeight();
		GLine bottomline = new GLine(0, y - GRAPH_MARGIN_SIZE, x, y - GRAPH_MARGIN_SIZE);
		GLine topline = new GLine(0, GRAPH_MARGIN_SIZE, x, GRAPH_MARGIN_SIZE);
		add(bottomline);
		add(topline);
		//Draws vertical lines.
		for (int i = 0; i <= NDECADES; i++) {
			double vx = i * (x / NDECADES);
			GLine line = new GLine( vx, 0, vx, y);
			add(line);
		}
	}
	
	private void drawBottomLabels() {
		int decade = START_DECADE;
		// LABEL_OFFSET give the program a cleaner look.
		double label_x = LABEL_OFFSET;
		double y = getHeight() - LABEL_OFFSET;
		while (true) {
			GLabel label = new GLabel(""+ decade +"", label_x, y);
			add(label);
			decade += 10;
			label_x += (getWidth() / NDECADES);
			if (decade > 2000) break;		
		}
	}
	
	
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }
}
