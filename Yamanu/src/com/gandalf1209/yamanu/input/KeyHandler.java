package com.gandalf1209.yamanu.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	/**
	 * List of all buttons that can be pressed
	 */
	public boolean[] pressed = new boolean[68836];
	
	/**
	 * Creates a new KeyHandler
	 * @param thread Thread to be run by KeyHandler
	 */
	public KeyHandler(Thread thread) {
		thread.setName("KeyHandler Checker");
		thread.start();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (key > 0 && key <= pressed.length) {
			pressed[key] = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key > 0 && key <= pressed.length) {
			pressed[key] = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

}
