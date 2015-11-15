package model;

import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import myComponents.PhotoViewer;
import myComponents.FreeHand;

public class model {
	public static JFrame frame;
	public static boolean flipImage = false;// whether the image has been flipped
	public static boolean hasImage = false;// whether there is an image
	public static int width, height;
	public static String path;
	public static int image_width;
	public static int image_height;
	public static PhotoViewer imageViewer;
	public static JScrollPane sp;
	public static FreeHand freeHand = new FreeHand();
	public static MouseListener sketchMouseListener;
}
