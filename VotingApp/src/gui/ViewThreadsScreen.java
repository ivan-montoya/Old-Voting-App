/**
 * 
 */
package gui;

import Driver.RegisteredUser;
import Driver.GeneralUser;
import Driver.VotingThread;
import database.ThreadDataBase;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author Ivan Montoya
 *
 */
public class ViewThreadsScreen extends JFrame{
	
	/**Used to create components for each Thread*/
	private Map<JLabel, JButton> myThreadComponents;
	private ThreadDataBase myDatabase;
	
	public ViewThreadsScreen(ThreadDataBase database) {
		
		myDatabase = database;
		myThreadComponents = new HashMap<JLabel,JButton>();
		
		setThreads();
		
		start();
	}
	
	/**
	 * Sets up components for the JFrame.
	 */
	private void start() {
		
		GridLayout grid = new GridLayout(myThreadComponents.size(), 2);
		
		this.setLayout(grid);
		for (JLabel title: myThreadComponents.keySet()) {
			this.add(title);
			this.add(myThreadComponents.get(title));
		}
		this.setTitle("View Threads");
		
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setVisible(true);
	}
	
	/**
	 * Uses a List of threads to create JLabels and JButtons
	 * for each thread.
	 */
	private void setThreads() {
		for (VotingThread thread: myDatabase.getThreadsFromDatabase()) {
			JLabel tempLabel = new JLabel("Thread Title:    " + thread.getTitle());
			myThreadComponents.put(tempLabel, createViewButton(thread));
		}
	}
	
	private JButton createViewButton(VotingThread theThread) {
		JButton tempButton = new JButton("view");
		tempButton.addActionListener(viewButtonAction(theThread));
		
		return tempButton;
	}
	
	private ActionListener viewButtonAction(VotingThread theThread) {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CurrentThreadScreen(theThread);
			}
		};
	}
	
}
