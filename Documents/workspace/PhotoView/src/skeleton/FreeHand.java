//used to draw strokes
package skeleton;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;

public class FreeHand extends JPanel {

	BufferedImage image;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image == null)
			initImage();
		g.drawImage(image, 0, 0, this);

	}

	private void initImage() {
		image = new BufferedImage(Frame.image_width, Frame.image_height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(new Color(255, 255, 255));
		g2.fillRect(0, 0, Frame.image_width, Frame.image_height);// the way to
																	// pass
		// g2.setPaint(getBackground());
		g2.dispose();
	}

	public void draw(Point start, Point end) {
		Graphics2D g2 = image.createGraphics();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setPaint(Color.black);
		g2.draw(new Line2D.Double(start, end));
		g2.dispose();
		repaint();
	}

}

class DrawingListener extends MouseInputAdapter {
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
			Frame.frame.remove(freeHand);
			Frame.flipImage = false;
			Frame.imageViewer = new PhotoViewer(Frame.path);
			Frame.imageViewer.setSize(new Dimension(Frame.image_width, Frame.image_height));
			Frame.imageViewer.setPreferredSize(new Dimension(Frame.image_width, Frame.image_height));
			Frame.sp = new JScrollPane(Frame.imageViewer);// put the imageviewer
															// into a
			// scrollpane
			Frame.sp.addMouseListener(Frame.spMouseListener);
			Frame.frame.add(Frame.sp, BorderLayout.CENTER);
			Frame.frame.pack();// pack is necessary after adding the
								// widget to the frame
			Frame.frame.repaint();
		}

	}
}