package com.gandalf1209.yamanu.handlers;

import java.awt.event.ActionListener;

import javax.swing.Timer;

/**
 * Handles certain game aspects
 * @author Gandalf1209
 */
public class GameHandler {

	private static Timer t;
	
	/**
	 * Sets Swing Timer for ActionListener to have the game repaint()
	 * and work the way it's supposed to.
	 * @param time Game Time
	 * @param al ActionListener
	 */
	public void initTimer(int time, ActionListener al) {
		t = new Timer(time, al);
		t.start();
	}
	
	/**
	 * Pauses the game, and any background music.
	 */
	public void pauseGame() {
		t.stop();
		Sound s = new Sound();
		s.setBGPaused(true);
	}
	
	/**
	 * Resumes the game, and any background music.
	 */
	public void resumeGame() {
		t.start();
		Sound s = new Sound();
		s.setBGPaused(false);
	}
	
}
