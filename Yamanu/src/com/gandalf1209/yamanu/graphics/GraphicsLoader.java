package com.gandalf1209.yamanu.graphics;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import com.gandalf1209.yamanu.util.Log;
import com.gandalf1209.yamanu.util.SystemUtil;

public class GraphicsLoader {

	private static String defDir = "";
	private SystemUtil util = new SystemUtil();
	
	/**
	 * Load an image easily
	 * @param path Path to Image
	 * @return Image
	 */
	public Image loadGraphic(String path) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(getClass().getResourceAsStream(defDir + path));
		} catch (Exception e) {
			Log.err("Yamanu: " + e.getMessage());
			Log.err("Yamanu Version: " + util.getYGEVersion());
			e.printStackTrace();
		}
		return img;
	}
	
	/**
	 * Set the directory for loading images with the loadGraphic method
	 * @param path Path to Directory
	 */
	public void setDefaultLoadingDirectory(String path) {
		defDir = path;
	}
	
}
