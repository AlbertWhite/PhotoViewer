//MVC 3 classes model:annotion, drawing, whether it is flipped
//增加slider在两侧。重新调整布局
//自定义写字的控件

package skeleton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

public class Frame {

	static JFrame frame;
	static PhotoViewer imageViewer;
	static JScrollPane sp;
	private static JToggleButton toggleButton = null;
	private static boolean hasImage = false;// whether there is an image
	static boolean flipImage = false;// whether the image has been flipped
	static int width, height;
	static String path;
	static int image_width, image_height;
	static MouseListener spMouseListener;
	private static FreeHand freeHand = new FreeHand();

	private static void addPhotoViewerComponent(JFileChooser fc) {
		path = fc.getSelectedFile().getAbsolutePath();// get the path of

		// if there already exists image, then the old image should be deleted
		if (hasImage) {
			frame.remove(sp);
		}

		imageViewer = new PhotoViewer(path);
		imageViewer.setSize(new Dimension(image_width, image_height));
		imageViewer.setPreferredSize(new Dimension(image_width, image_height));
		sp = new JScrollPane(imageViewer);// put the imageviewer into a
											// scrollpane
		frame.add(sp, BorderLayout.CENTER);
		hasImage = true;
		frame.pack();
		frame.setVisible(true);

		spMouseListener = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					if (hasImage) {
						if (!flipImage) {
							frame.remove(sp);
							flipImage = true;
							DrawingListener listener = new DrawingListener(freeHand);
							freeHand.addMouseListener(listener);
							freeHand.addMouseMotionListener(listener);
							freeHand.setPreferredSize(new Dimension(image_width, image_height));
							frame.add(freeHand);
							frame.pack();
							frame.repaint();// repaint is refresh the current

						}
					}
				}
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
		};

		sp.addMouseListener(spMouseListener);
	}

	private static void createAndShowGUI() {
		frame = new JFrame("Photo Viewer");
		frame.setSize(600, 600);

		// title menu
		JMenu fileManu = new JMenu("File");
		JMenuItem importImage = new JMenuItem("Import");
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

		frame.setLayout(new BorderLayout());
		//
		frame.add(fileMenuBar, BorderLayout.NORTH);
		frame.add(statusPanel, BorderLayout.SOUTH);
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
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Create the frame.

		createAndShowGUI();

	}

	private static JToggleButton getColdWarmButton() {
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
