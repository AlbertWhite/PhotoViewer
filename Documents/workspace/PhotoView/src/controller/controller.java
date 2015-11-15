package controller;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFileChooser;
import javax.swing.JScrollPane;

import model.model;
import myComponents.PhotoViewer;
import view.View;
import myComponents.DrawingListener;

public class controller {

	public ActionListener importImageListener;

	public void init() {

		model.sketchMouseListener = new MouseListener() {

			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2 && !e.isConsumed()) {
					e.consume();
					if (model.hasImage) {
						if (!model.flipImage) {
							model.frame.remove(model.sp);
							model.flipImage = true;
							DrawingListener listener = new DrawingListener(model.freeHand);
							model.freeHand.addMouseListener(listener);
							model.freeHand.addMouseMotionListener(listener);
							model.freeHand.setPreferredSize(new Dimension(model.image_width, model.image_height));
							model.frame.add(model.freeHand);
							model.frame.pack();
							model.frame.repaint();// repaint is refresh the
													// current

						}
					}
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		};

	}

	public void addPhotoViewerComponent(JFileChooser fc){
		model.path = fc.getSelectedFile().getAbsolutePath();// get the path of

		// if there already exists image, then the old image should be deleted
		if (model.hasImage) {
			model.frame.remove(model.sp);
		}

		model.imageViewer = new PhotoViewer(model.path);
		model.imageViewer.setSize(new Dimension(model.image_width, model.image_height));
		model.imageViewer.setPreferredSize(new Dimension(model.image_width, model.image_height));
		model.sp = new JScrollPane(model.imageViewer);// put the imageviewer into a
		// scrollpane
		model.frame.add(model.sp, BorderLayout.CENTER);
		model.hasImage = true;
		model.frame.pack();
		model.frame.setVisible(true);

		model.sp.addMouseListener(model.sketchMouseListener);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final model model = new model();
		View view = new View();
		final controller controller = new controller();
		
		controller.init();
		controller.importImageListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				final JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(model.frame);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					controller.addPhotoViewerComponent(fc);
				} else {
					System.out.println("Couldn't find the file");
				}

			}
		};

		view.importImage.addActionListener(controller.importImageListener);

	}

}
