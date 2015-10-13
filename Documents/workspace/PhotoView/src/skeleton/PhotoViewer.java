package skeleton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class PhotoViewer extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BufferedImage image;
	
	//the constructor of the class.
	public PhotoViewer(String path){
		try {
			System.out.println(path);
			this.image = ImageIO.read(new File(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Couldn't find the file: "+path);
		}
	}
	
	//called when the component is initialized
	protected void paintComponent(Graphics g){
		 g.drawImage(image, 0, 0, null); //render the image
	}
    

    
	
	protected static void createAndShowGUI(){
		JFrame frame = new JFrame("customized_photoviewer");
		
		PhotoViewer imageViewer = new PhotoViewer("image/space.jpg");
		
		frame.getContentPane().add(imageViewer, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	

}
