package com.gandalf1209.yamanu.handlers;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

import com.gandalf1209.yamanu.util.Log;
import com.gandalf1209.yamanu.util.SystemUtil;

/**
 * Sound engine, allowing sound effects and background
 * music, and handling
 * @author Gandalf1209
 */
public class Sound {
	
	private Clip bg;
	private String defDir = "";
	
	private SystemUtil util;

	/**
	 * Plays the audio file specified in the 'url' parameter. If the
	 * audio file is background music, set 'isBG' to true. Otherwise
	 * keep it false.
	 * @param url URL for Audio Loading
	 * @param isBG Set as Background Music
	 */
	public void play(final String url, boolean isBG) {
		try {
			InputStream in = getClass().getResourceAsStream(defDir + url);
			InputStream buf = new BufferedInputStream(in);
			AudioInputStream ain = AudioSystem.getAudioInputStream(buf);
			AudioFormat format = ain.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(ain);
			if (isBG) {
				bg = clip;
			}
			clip.start();
		} catch (Exception e) {
			Log.err("Yamanu: " + e.getMessage());
			Log.err("Yamanu Version: " + util.getYGEVersion());
			e.printStackTrace();
		}
	}
	
	public void play(final AudioFile file) {
		try {
			file.clip.open(file.getAIN());
			file.clip.start();
			if (file.getBG()) {
				Thread bgThread = new Thread("Background Music Thread") {
					public void run() {
						while (file.clip.isActive()) {
							if (file.clip.getFramePosition() == file.clip.getFrameLength()) {
								file.clip.setFramePosition(0);
							}
						}
					}
				};
				bgThread.start();
			}
		} catch (Exception e) {
			Log.err("Yamanu: " + e.getMessage());
			Log.err("Yamanu Version: " + util.getYGEVersion());
			e.printStackTrace();
		}
	}
	
	/**
	 * Set the default directory for loading sounds. In the play method,
	 * it will add the default directory to the url provided in the parameters.
	 * @param path Path to Directory
	 */
	public void setDefaultLoadingDirectory(String path) {
		defDir = path;
	}
	
	/**
	 * Put this in a loop such as a thread or a timer to tell
	 * Yamanu to loop the background music
	 */
	public void loopBG() {
		if (bg.getFramePosition() == bg.getFrameLength()) {
			bg.setFramePosition(0);
		}
	}
	
	/**
	 * Use this to set whether or not the background music
	 * is paused. Good for pause menus and cut scenes.
	 * @param paused Set Paused State
	 */
	public void setBGPaused(boolean paused) {
		if (paused) {
			bg.stop();
		} else {
			bg.start();
		}
	}
	
	/**
	 * Returns the current paused state of the background
	 * music
	 * @return boolean
	 */
	public boolean getBGPaused() {
		if (bg.isRunning()) {
			return false;
		} else {
			return true;
		}
	}

}
