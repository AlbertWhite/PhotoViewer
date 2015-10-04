package skeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;

public class Frame {
	
	private static JFrame frame;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//Create the frame.
		frame = new JFrame("Photo Viewer");
		frame.setSize(1000, 500);

		JPanel titlePanel = new JPanel();
		
		//JMenuBar includes JMenu, and JMenu includes JMenuItem
		JMenu fileManu = new JMenu("File");
		JMenuItem importImage = new JMenuItem("Import");
		JMenuItem delete = new JMenuItem("Delete");
		JMenuItem quit = new JMenuItem("Quit");		
		JMenuBar fileMenuBar = new JMenuBar();
		


		fileManu.add(importImage);
		fileManu.add(delete);
		fileManu.add(quit);
		
		fileMenuBar.add(fileManu);
		fileMenuBar.setPreferredSize(new Dimension(1000, 25));
		
			
		JRadioButton photoViewer = new JRadioButton("Photo Viewer");
		JRadioButton browser = new JRadioButton("Browser");
		JRadioButton splitmode = new JRadioButton("Split Mode");
		
		JLabel viewMode =  new JLabel("     View Mode |");
		fileMenuBar.add(viewMode);
		fileMenuBar.add(photoViewer);
		fileMenuBar.add(browser);
		fileMenuBar.add(splitmode);
		
		titlePanel.add(fileMenuBar);
//		titlePanel.add(photoViewer);
//		titlePanel.add(browser);
//		titlePanel.add(splitmode);
		
		//status bar
		JPanel statusPanel = new JPanel();
		JLabel label = new JLabel("Status Bar");
		statusPanel.add(label);
		
		//sidebar bar
		JPanel sidePanel = new JPanel();
		JToggleButton family = new JToggleButton("Family");
		JToggleButton vacation = new JToggleButton("Vacation");
		sidePanel.add(family);
		sidePanel.add(vacation);
		
		frame.setLayout(new BorderLayout());
		frame.add(titlePanel,BorderLayout.NORTH);
		frame.add(statusPanel,BorderLayout.SOUTH);
		frame.add(sidePanel,BorderLayout.WEST);
		frame.setVisible(true);//if not, frame won't show up
		
		importImage.addActionListener(new ActionListener()
		{
			  public void actionPerformed(ActionEvent e)
			  {
			    final JFileChooser fc = new JFileChooser();
			    fc.showOpenDialog(frame);
			  }
			});
		
		
	}
	


}
