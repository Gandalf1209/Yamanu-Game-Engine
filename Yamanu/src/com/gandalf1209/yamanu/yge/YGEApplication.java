package com.gandalf1209.yamanu.yge;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;

import com.gandalf1209.yamanu.graphics.GraphicsHandler;
import com.gandalf1209.yamanu.graphics.GraphicsLoader;
import com.gandalf1209.yamanu.handlers.GameHandler;
import com.gandalf1209.yamanu.handlers.JVMRuntimeHandler;
import com.gandalf1209.yamanu.handlers.Sound;
import com.gandalf1209.yamanu.input.KeyHandler;
import com.gandalf1209.yamanu.physics.PhysicsHandler;
import com.gandalf1209.yamanu.util.Debug;
import com.gandalf1209.yamanu.util.SystemUtil;

@SuppressWarnings("serial")
public class YGEApplication extends JComponent implements ActionListener {
	
	protected static JFrame frame = new JFrame();
	protected static Window window = new Window();
	protected static SystemUtil util = new SystemUtil();
	protected static JVMRuntimeHandler runtime = new JVMRuntimeHandler();
	protected static GameHandler gameHandler = new GameHandler();
	protected static GraphicsHandler graphics;
	protected static GraphicsLoader graphicLoader = new GraphicsLoader();
	protected static Sound sound = new Sound();
	protected static PhysicsHandler physics;
	protected static KeyHandler keyHandler;
	
	protected static Thread keyThread;
	private static boolean esc;
	
	private static Thread load;
	private static Class<?> gameClass;
	
	/**
	 * Initializes default key handler thread
	 */
	protected void initDefThread() {
		keyThread = new Thread("YGE Key Handler") {
			public void run() {
				while (true) {
					esc = keyHandler.pressed[KeyEvent.VK_ESCAPE];
				
					if (esc) {
						exit();
					}
				}
			}
		};
	}
	
	/**
	 * Initializes the timer
	 * @param time Time
	 */
	protected void initTime(int time) {
		gameHandler.initTimer(40, this);
	}
	
	/**
	 * Initializes the graphics quicker
	 * @param g Graphics
	 * @param c	Component
	 */
	protected void initGraphics(Graphics g, Component c) {
		graphics = new GraphicsHandler(g, c);
	}
	
	/**
	 * Initializes the key handler, and automatically adds it to your component
	 * @param thread Thread
	 * @param c Component
	 */
	protected void initKeyHandler(Thread thread, Component c) {
		keyHandler = new KeyHandler(thread);
		c.addKeyListener(keyHandler);
	}
	
	/**
	 * Correctly exits a YGEApplication
	 */
	public static void exit() {
		runtime.exit(gameClass);
	}
	
	/**
	 * Correctly starts a YGEApplication. Do BEFORE EVERYTHING
	 */
	public static void start(Class<?> c) {
		gameClass = c;
		Debug.initClass(c);
		load = new Thread("Yamanu Loader") {
			public void run() {
				try {
					util.checkFiles();
					physics = new PhysicsHandler();
				} catch (Exception e) {
					Debug.pst(e);
				}
			}
		};
		load.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		runtime.cleanup();
		
		repaint();
		
	}
	
}
