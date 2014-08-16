package com.gandalf1209.yamanu.yge;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.gandalf1209.yamanu.util.FileLoader;
import com.gandalf1209.yamanu.util.SystemUtil;

public class Cache extends SystemUtil {

	public static void createCacheFile(String fileName) {
		File file = new File(path + fileName);
		try {
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void forceCreateCacheFile(String fileName) {
		File file = new File(path + fileName);
		try {
			file.createNewFile();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToCacheFile(String fileName, String content) {
		File file = new File(path + fileName);
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter(file));
			bw.write(content);
			bw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static String readFromCacheFile(String fileName) {
		return new FileLoader().loadText(fileName);
	}
	
}
