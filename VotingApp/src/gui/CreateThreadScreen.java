package gui;

import Driver.RegisteredUser;
import Driver.GeneralUser;
import Driver.VotingThread;
import database.ThreadDataBase;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateThreadScreen extends JFrame {
	
	/* READ THIS! IMPORTANT
	 * 
	 * This class should save the information in
	 * the JTextFields inside a new Thread then
	 * pass that Thread to MainScreen.
	 */
	private JTextField myTitle;
	private JTextArea myDescription;
	
	private JPanel myPanel;
	private JButton myCreateButton;
	
	private int myOptionsAmount;
	private List<JTextField> myOptions;

	private ThreadDataBase myDatabase;
	
	public CreateThreadScreen(ThreadDataBase database) {
		myDatabase = database;
		myPanel = new JPanel();
		myOptions = new LinkedList<JTextField>();
		createJFrame();
	}
	
	/**
	 * Sets up and starts JFrame Window.
	 */
	private void createJFrame() {
		myPanel.setLayout(new BoxLayout(myPanel, BoxLayout.PAGE_AXIS));
		
		JPanel titlePanel = createTitlePanel();
		myPanel.add(titlePanel);
		
		JPanel descriptionPanel = createDescriptionPanel();
		myPanel.add(descriptionPanel);
		
		JPanel optionPanel = new JPanel(new FlowLayout());
		
		
		JTextField tempField = new JTextField(25);
		myOptions.add(tempField);
		optionPanel.add(new JLabel("Option 1:"));
		optionPanel.add(tempField);
		myPanel.add(optionPanel);
		
		// More Components
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 2));
		buttonPanel.add(addOptionButton());
		buttonPanel.add(addCreateThreadButton());
		this.add(myPanel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.setTitle("Create Thread");
		
		this.pack();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        
        this.setVisible(true);
	}
	
	private JPanel createTitlePanel() {
		JPanel titlePanel = new JPanel();
		myTitle = new JTextField(35);
		titlePanel.add(new JLabel("Title:            ."));
		titlePanel.add(myTitle);
		
		return titlePanel;
	}
	private JPanel createDescriptionPanel() {
		JPanel descriptionPanel = new JPanel();
		myDescription = new JTextArea(2, 35);
		descriptionPanel.add(new JLabel("Description:"));
		descriptionPanel.add(myDescription);
		
		return descriptionPanel;
	}
	
	/**
	 * Sets necessary elements for the Add option button.
	 */
	public JButton addOptionButton() {
		JButton addOptionButton = new JButton("Add Option");
		addOptionButton.addActionListener(addOptionAction());
		
		return addOptionButton;
	}
	
	private ActionListener addOptionAction() {
		return  new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JTextField tempField = new JTextField(25);
				myOptions.add(tempField);
				
				JPanel optionPanel = new JPanel(new FlowLayout());
				optionPanel.add(new JLabel("Option " + myOptions.size() + ":"));
				
				optionPanel.add(tempField);
				myPanel.add(optionPanel);
				
				revalidate();
				pack();
			}
		};
	}
	
	/**
	 * Sets up Create Thread button.
	 */
	public JButton addCreateThreadButton() {
		JButton createButton = new JButton("Create Thread");
		createButton.addActionListener( createThreadAction());
		
		return createButton;
	}
	
	private ActionListener createThreadAction() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Iterates through Options and saves text
				String[] optionsArray = new String[myOptions.size()];
				for (int i = 0; i < myOptions.size(); i++) {
					optionsArray[i] = myOptions.get(i).getText();
				}
				
				myDatabase.addVotingThread(new VotingThread(myTitle.getText(), myDescription.getText(),
						optionsArray, null, myOptions.size()));
				
				JOptionPane.showMessageDialog(null, "Thread Created.");
				myDatabase.saveNewThreads();
				dispose();
			}
		};
	}
}
