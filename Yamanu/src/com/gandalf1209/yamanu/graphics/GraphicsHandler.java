package com.gandalf1209.yamanu.graphics;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import com.gandalf1209.yamanu.game.GameObject;
import com.gandalf1209.yamanu.handlers.GameHandler;
import com.gandalf1209.yamanu.yge.Window;

@SuppressWarnings("serial")
public class GraphicsHandler extends Window {
	
	public final int SMART_SHADOW = -801;
	
	private Graphics g;
	private Component c;
	private GraphicsLoader gl;
	private GameHandler gh;
	
	/**
	 * Initialize the class, and set where to draw the graphics
	 * @param g Graphics (paintComponent(), paint())
	 * @param c Component (Window, JFrame, Component)
	 */
	public GraphicsHandler(Graphics g, Component c) {
		this.g = g;
		this.c = c;
		gl = new GraphicsLoader();
		gl.setDefaultLoadingDirectory("/textures/");
		gh = new GameHandler();
	}
	
	/**
	 * Set the background color
	 * @param color Background Color
	 */
	public void setBGColor(Color color) {
		g.setColor(color);
		g.fillRect(0, 0, c.getWidth(), c.getHeight());
	}
	
	/**
	 * Set the background to an image
	 * @param img Image
	 */
	public void setBGImage(Image img) {
		g.drawImage(img, 0, 0, c.getWidth(), c.getHeight(), null);
	}
	
	/**
	 * Add a light to the screen. Note: Must manually add shadows
	 * @param x Light X
	 * @param y Light Y
	 * @param intense Light Intensity
	 */
	public void addLight(int x, int y, int intense) {
		BufferedImage light = (BufferedImage) gl.loadGraphic("light.png");
		g.drawImage(light, x, y, intense, intense, null);
	}
	
	/**
	 * Add an image to the screen.
	 * @param img image
	 * @param x Location X
	 * @param y Location Y
	 * @param w Width
	 * @param h Height
	 */
	public void addImage(Image img, int x, int y, int w, int h) {
		g.drawImage(img, x, y, w, h, null);
	}
	
	/**
	 * Add an image with an added shadow. Use YGE_SMART_SHADOW_LOC as one or both
	 * intensities to have the shadow automatically resize and relocate according
	 * to the image.
	 * @param img Image
	 * @param x Image X
	 * @param y Image Y
	 * @param w Image Width
	 * @param h Image Height
	 * @param shIntenseX Intensity X
	 * @param shIntenseY Intensity Y
	 */
	public void addImageWithShadow(Image img, int x, int y, int w, int h, int shadowX, int shadowY, int shadowW, int shadowH) {
		BufferedImage shadow = (BufferedImage) gl.loadGraphic("shadow.png");
		if (shadowW == SMART_SHADOW || shadowH == SMART_SHADOW || shadowX == SMART_SHADOW || shadowY == SMART_SHADOW) {
			g.drawImage(shadow, x + (w / 2), y + (h / 5), w - (w / 5), h - (h / 5), null);
		} else {
			g.drawImage(shadow, shadowX, shadowY, shadowW, shadowH, null);
		}
		g.drawImage(img, x, y, w, h, null);
	}
	
	/**
	 * Make a custom popup message instead of Swing's JOptionPane
	 * @param message Use array for different lines
	 * @param messageColor Color of text
	 * @param fill Fill Color
	 * @param border Border Color
	 * @param whenShown Variable to trigger popup
	 * @param pauseOnShow Pause game on popup?
	 * @param x Position X
	 * @param y Position Y
	 * @param w Width of Popup
	 * @param h Height of Popup
	 */
	public void addCustomPopup(String[] message, Color messageColor, Color fill, Color border, boolean whenShown, boolean pauseOnShow, 
							   int x, int y, int w, int h) {
		if (whenShown) {
			g.setColor(fill);
			g.fillRect(x, y, w, h);
			g.setColor(border);
			g.drawRect(x, y, w, h);
			g.setColor(messageColor);
			for (int i = 0; i < message.length; i++) {
				g.drawString(message[i], x + 10, y + (i * 15));
			}
			if (pauseOnShow) {
				gh.pauseGame();
			}
		} else {
			gh.resumeGame();
		}
	}
	
	/**
	 * Draws a rectangular prism
	 * @param x Pos X
	 * @param y Pos Y
	 * @param w Width
	 * @param h Height
	 * @param color Color
	 */
	public void draw3DRect(int x, int y, int w, int h, Color color) {
		g.setColor(color);
		g.fillRect(x, y, w, h);
		g.setColor(Color.black);
		g.drawRect(x, y, w, h);
		g.drawLine(x, y, x + (w / 3), y - (h / 3));
		g.drawLine(x + w, y, (x + w) + (w /3), y - (h / 3));
		g.drawLine(x + w, y + h, (x + w) + (w / 3), (y + h) - (h /3));
		g.drawLine(x + (w / 3), y - (h / 3), x + (w / 3) + w, y - (h / 3));
		g.drawLine(x + (w / 3) + w, y - (h / 3), x + (w / 3) + w, y - (h / 3) + h);
	}
	
	public void addGameObject(GameObject obj) {
		g.drawImage(obj.getImage(), obj.getX(), obj.getHeight(), obj.getWidth(), obj.getHeight(), null);
	}
	
}
