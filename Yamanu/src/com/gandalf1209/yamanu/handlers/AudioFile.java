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

public class AudioFile {
	
	public Clip clip;
	private boolean isBG = false;
	
	private static SystemUtil utils = new SystemUtil();
	
	public AudioFile(String url) {
		try {
			InputStream in = getClass().getResourceAsStream(url);
			InputStream buf = new BufferedInputStream(in);
			AudioInputStream ain = AudioSystem.getAudioInputStream(buf);
			AudioFormat format = ain.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			this.clip = (Clip) AudioSystem.getLine(info);
		} catch (Exception e) {
			Log.err("Yamanu: " + e.getMessage());
			Log.err("Yamanu Version: " + utils.getYGEVersion());
			e.printStackTrace();
		}
	}
	
	public void setBG() {
		this.isBG = true;
	}
	
	public boolean getBG() {
		return this.isBG;
	}
	
}
