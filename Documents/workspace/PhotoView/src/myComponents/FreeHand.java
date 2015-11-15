//used to draw strokes
package myComponents;

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
import model.model;

public class FreeHand extends JPanel {

	BufferedImage image;

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (image == null)
			initImage();
		g.drawImage(image, 0, 0, this);

	}

	private void initImage() {
		image = new BufferedImage(model.image_width, model.image_height, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = image.createGraphics();
		g2.setColor(new Color(255, 255, 255));
		g2.fillRect(0, 0, model.image_width, model.image_height);// the way to
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
