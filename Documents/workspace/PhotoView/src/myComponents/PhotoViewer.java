//not sure why the height and width of the rectangle is not right
//not sure how to get parameter and send it to paint component to control the workflo
package myComponents;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;
import model.model;

public class PhotoViewer extends JComponent {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	private boolean flipImage = false;

	// the constructor of the class.
	public PhotoViewer(String path) {
		try {
			this.image = ImageIO.read(new File(path));
			model.image_height = image.getHeight();
			model.image_width = image.getWidth();
			System.out.println("done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't find the file: " + path);
		}
	}

	// called when the component is initialized
	protected void paintComponent(Graphics g) {
		flipImage = model.flipImage;// get the value everytime the code needs it
		if (!flipImage) {
			g.drawImage(image, 0, 0, null); // render the image
		} else {// never goes here, never get the parameter received from the
				// software
			g.setColor(new Color(255, 255, 255));
			g.fillRect(0, 0, model.image_width, model.image_height);// the way to pass
														// global parameters
		
			
		}
	}

}
