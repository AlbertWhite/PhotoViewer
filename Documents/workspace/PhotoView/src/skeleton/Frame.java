package skeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;


public class Frame {

	private static JFrame frame;
	private static PhotoViewer imageViewer;
	private static boolean hasImage = false;// whether there is an image
	static boolean flipImage = false;// whether the image has been flipped
	static int width, height;
	private static String path;
	private static FreeHand freeHand = new FreeHand();
	
	private static void addPhotoViewerComponent(JFileChooser fc) {
		path = fc.getSelectedFile().getAbsolutePath();// get the path of
		
		// if there already exists image, then the old image should be deleted
		if (hasImage) {
			frame.remove(imageViewer);
		}

		imageViewer = new PhotoViewer(path);
		hasImage = true;
		//JPanel p = new ScrollImageTest();
		//frame.add(p);
		frame.add(imageViewer, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
		
	}

	private static void createAndShowGUI() {
		frame = new JFrame("Photo Viewer");
		frame.setSize(1000, 500);
		frame.setMinimumSize(new Dimension(1000,500));

		JPanel titlePanel = new JPanel();

		// JMenuBar includes JMenu, and JMenu includes JMenuItem
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

		JLabel viewMode = new JLabel("     View Mode |");
		fileMenuBar.add(viewMode);
		fileMenuBar.add(photoViewer);
		fileMenuBar.add(browser);
		fileMenuBar.add(splitmode);

		titlePanel.add(fileMenuBar);

		// status bar
		JPanel statusPanel = new JPanel();
		JLabel label = new JLabel("Status Bar");
		statusPanel.add(label);

		// sidebar bar
		JPanel sidePanel = new JPanel();
		JToggleButton family = new JToggleButton("Family");
		JToggleButton vacation = new JToggleButton("Vacation");
		sidePanel.add(family);
		sidePanel.add(vacation);

		frame.setLayout(new BorderLayout());
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(statusPanel, BorderLayout.SOUTH);
		frame.add(sidePanel, BorderLayout.WEST);
		frame.setVisible(true);// if not, frame won't show up

		// action listener on the button of import image
		importImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					addPhotoViewerComponent(fc);
				} else {
					System.out.println("Couldn't find the file");
				}

			}
		});
		
		frame.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
								
				if (hasImage) {
					if(!flipImage){
						
				        DrawingListener listener = new DrawingListener(freeHand);
				        freeHand.addMouseListener(listener);
				        freeHand.addMouseMotionListener(listener);
				        
				        freeHand.addMouseListener(new MouseListener() {

							@Override
							public void mouseClicked(MouseEvent e) {
								// TODO Auto-generated method stub
								System.out.println(e.getY()+e.getX());
								
								
								JTextField text = new JTextField("click to input");
								freeHand.setLayout(null);
								freeHand.add(text);
								Dimension size = text.getPreferredSize();
								text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
								text.setBounds(e.getX(),e.getY(),200,20);
								frame.pack();
								frame.repaint();
							}

							@Override
							public void mousePressed(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseReleased(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseEntered(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}

							@Override
							public void mouseExited(MouseEvent e) {
								// TODO Auto-generated method stub
								
							}
				        	
				        });
						
						height = imageViewer.getHeight();
						width = imageViewer.getWidth();
						frame.remove(imageViewer);
						flipImage = true;
						imageViewer = new PhotoViewer(path);
						//frame.add(imageViewer, BorderLayout.CENTER);
						frame.add(freeHand,BorderLayout.CENTER);
						frame.pack();
						frame.repaint();//repaint is refresh the current interface
						


						
					}else{
						frame.remove(freeHand);
						frame.remove(imageViewer);
						flipImage = false;
						imageViewer = new PhotoViewer(path);
						frame.add(imageViewer, BorderLayout.CENTER);
						frame.pack();//pack is necessary after adding the widget to the frame
						frame.repaint();
						
					}
				
				}
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				
			}
		});
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create the frame.

		createAndShowGUI();

	}

}
