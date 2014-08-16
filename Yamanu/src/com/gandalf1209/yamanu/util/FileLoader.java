package com.gandalf1209.yamanu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class FileLoader {

	private static String defDir = "";
	private SystemUtil util = new SystemUtil();
	
	/**
	 * Load a file
	 * @param loc File location
	 * @return File
	 */
	public File load(String url) {
		File file = new File(defDir + url);
		return file;
	}
	
	/**
	 * Returns the contents of a text file
	 * @param url File Location
	 * @return String
	 */
	public String loadText(String url) {
		StringBuilder source = new StringBuilder();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(defDir + url));
			String line;
			while ((line = reader.readLine()) != null) {
				source.append(line).append("\n");
			}
			reader.close();
		} catch (Exception e) {
			Log.err("Yamanu: " + e.getMessage());
			Log.err("Yamanu Version: " + util.getYGEVersion());
			e.printStackTrace();
		}
		return source.toString();
	}
	
	/**
	 * Set the default directory for loading files
	 * @param path Directory Path
	 */
	public void setDefaultLoadingDirectory(String path) {
		defDir = path;
	}
	
}
