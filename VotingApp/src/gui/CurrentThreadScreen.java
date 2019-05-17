/**
 * 
 */
package gui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;


import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;

import Driver.VotingThread;

/**
 * @author Ivan Montoya
 *
 */
public class CurrentThreadScreen extends JFrame {
	
	VotingThread myThread;
	
	public CurrentThreadScreen(VotingThread theThread) {
		myThread = theThread;
		constructJFrame();
		setupScreenOptions();
	}
	
	private void constructJFrame() {
		GridLayout mainGrid = new GridLayout(myThread.getNumCandidates() + 2, 2);
		this.setLayout(mainGrid);
		this.add(new JLabel("Title: "));
		this.add(new JLabel(myThread.getTitle()));
		this.add(new JLabel("Description: "));
		this.add(new JLabel(myThread.getDescription()));
		
		ButtonGroup group = new ButtonGroup();
		for(int i = 0; i < myThread.getCandidates().length; i++) {
			if (myThread.getCandidates()[i] != "") {
				JRadioButton temp = new JRadioButton(myThread.getCandidates()[i]);
				group.add(temp);
				this.add(temp);
			}
		}
	}
	
	private void setupScreenOptions() {
		this.setTitle(myThread.getTitle());
		
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setVisible(true);
	}
}
