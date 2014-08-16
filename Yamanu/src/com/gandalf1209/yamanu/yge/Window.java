package com.gandalf1209.yamanu.yge;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.gandalf1209.yamanu.util.SystemUtil;

/**
 * Allows easy creation and control of windows
 * @author Gandalf1209
 */
@SuppressWarnings("serial")
public class Window extends JFrame {
	
	private static SystemUtil su = new SystemUtil();
	private static String ssPath;

	/**
	 * Makes a new window with the required parameters to have it
	 * appear and function on screen.
	 * @param title Window Title
	 * @param w Width
	 * @param h Height
	 * @param locRelTo Location Relative To (Set to null to center)
	 * @param closeOp Close Operation
	 * @return Window
	 */
	public void newFrame(String title, int w, int h, Component locRelTo, int closeOp) {
		this.setTitle(title);
		this.setSize(w, h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(closeOp);
		this.setIconImage(getIconImage());
	}
	
	/**
	 * Makes a new window with the required parameters to have it
	 * appear and function on screen.
	 * @param title Window Title
	 * @param w Width
	 * @param h Height
	 * @param locRelTo Location Relative To (Set to null to center)
	 * @param closeOp Close Operation
	 * @param comp To add a component on the fly!
	 * @return Window
	 */
	public void newFrame(String title, int w, int h, Component locRelTo, int closeOp, Component comp) {
		this.setTitle(title);
		this.add(comp);
		this.pack();
		this.setSize(w, h);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(closeOp);
	}
	
	/**
	 * Create a null Window
	 */
	public Window() {
		
	}
	
	/**
	 * Makes a simple window
	 * @param title String
	 */
	public Window(String title) {
		super(title);
	}
	
	/**
	 * Used to set the save location of screenshots in-game
	 * (Do NOT put a slash at the end of path!)
	 * @param path String
	 */
	public static void setScreenshotSaveLocation(String path) {
		ssPath = path;
	}
	
	/**
	 * Used to take a screenshot of the game window. Very effective!
	 * @param frame Window
	 * @throws Exception
	 */
	public static void screenshot(Window frame) throws Exception {
		Rectangle screen = new Rectangle(frame.getX(), frame.getY(), frame.getWidth(), frame.getHeight());
		BufferedImage capture = new Robot().createScreenCapture(screen);
		ImageIO.write(capture, "png", new File(ssPath + "/screenshot-" + su.getDate() + "_" + su.getTime()));
	}
	
	/**
	 * Can be used to get size needed for Window to be size of the 
	 * entire screen.
	 * @return Dimension
	 */
	public static Dimension screenSize() {
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		return new Dimension(gd.getDisplayMode().getWidth(), gd.getDisplayMode().getHeight() - 40);
	}
	
	/**
	 * Fixes graphical problem when frame is not able to be
	 * resized.
	 * @param frame Window
	 */
	public static void fixResize(Window frame) {
		if (System.getProperty("os.name").contains("Windows")) {
			frame.setSize(frame.getWidth() + 10, frame.getHeight() + 10);
		}
	}
	
	
	
}
