package myComponents;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

import model.model;

public class DrawingListener extends MouseInputAdapter {
	FreeHand freeHand;
	Point start;

	public DrawingListener(FreeHand fh) {
		this.freeHand = fh;
	}

	public void mousePressed(MouseEvent e) {
		start = e.getPoint();
	}

	public void mouseDragged(MouseEvent e) {
		Point p = e.getPoint();
		freeHand.draw(start, p);
		start = p;
	}

	public void mouseClicked(MouseEvent e) {

		if (e.getClickCount() == 2 && !e.isConsumed()) {
			e.consume();
			model.frame.remove(freeHand);
			model.flipImage = false;
			model.imageViewer = new PhotoViewer(model.path);
			model.imageViewer.setSize(new Dimension(model.image_width, model.image_height));
			model.imageViewer.setPreferredSize(new Dimension(model.image_width, model.image_height));
			model.sp = new JScrollPane(model.imageViewer);// put the imageviewer
															// into a
			// scrollpane
			model.sp.addMouseListener(model.sketchMouseListener);
			model.frame.add(model.sp, BorderLayout.CENTER);
			model.frame.pack();// pack is necessary after adding the widget to
								// the frame
			model.frame.repaint();
		} else {
//			System.out.println("y");
//			MyText text = new MyText();
//			System.out.println("xx");
//			text.setSize(text.getPreferredSize());
//			freeHand.setLayout(null);
//			freeHand.add(text);			
//			Frame.frame.pack();
//			Frame.frame.repaint();
			JTextField text = new JTextField("click to input");
			freeHand.setLayout(null);
			freeHand.add(text);
			text.setBorder(javax.swing.BorderFactory.createEmptyBorder());
			text.setBounds(e.getX() + 10, e.getY() + 10, 100, 20);
			model.frame.pack();
			model.frame.repaint();
		}
	}
}