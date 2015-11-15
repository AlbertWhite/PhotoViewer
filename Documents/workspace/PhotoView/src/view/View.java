package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JToggleButton;

import model.model;
public class View {
	
	public JMenuItem importImage;
	public JToggleButton toggleButton;
	
	
	public View() {
		createAndShowGUI(model.frame);
	}

	public void createAndShowGUI(JFrame frame) {
		model.frame = new JFrame("Photo Viewer");
		model.frame.setSize(600, 600);

		// title menu
		JMenu fileManu = new JMenu("File");
		importImage = new JMenuItem("Import");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem quit = new JMenuItem("Quit");
		fileManu.add(importImage);
		fileManu.add(delete);
		fileManu.add(quit);

		JMenu modeMenu = new JMenu("Mode");
		JRadioButtonMenuItem photoviewer = new JRadioButtonMenuItem("Photo Viewer", true);
		JRadioButtonMenuItem browser = new JRadioButtonMenuItem("Browser");
		JRadioButtonMenuItem split = new JRadioButtonMenuItem("Split Mode");
		ButtonGroup bg = new ButtonGroup();
		bg.add(photoviewer);
		bg.add(browser);
		bg.add(split);
		modeMenu.add(photoviewer);
		modeMenu.add(browser);
		modeMenu.add(split);

		JMenuBar fileMenuBar = new JMenuBar();

		fileMenuBar.add(fileManu);
		fileMenuBar.add(modeMenu);
		fileMenuBar.add(getColdWarmButton());

		// status bar
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.WHITE);
		JLabel label = new JLabel("Status Bar");
		statusPanel.add(label);

		model.frame.setLayout(new BorderLayout());
		//
		model.frame.add(fileMenuBar, BorderLayout.NORTH);
		model.frame.add(statusPanel, BorderLayout.SOUTH);
		model.frame.setVisible(true);// if not, frame won't show up

	}
	
	public JToggleButton getColdWarmButton() {
		if (toggleButton == null) {
			toggleButton = new JToggleButton();
			// coldWarmButton.setMinimumSize(coldWarmButton.getPreferredSize());
			// coldWarmButton.setPreferredSize(new Dimension(59, 59));

			toggleButton.setText("School");
			ItemListener coldWarmListener = new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (toggleButton.isSelected()) {
						toggleButton.setText("Vacation");
					} else {
						toggleButton.setText("School");
					}
				}
			};
			toggleButton.addItemListener(coldWarmListener);
		}
		return toggleButton;

	}
}
