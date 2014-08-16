package com.gandalf1209.yamanu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.gandalf1209.yamanu.yge.YGE;

public class SystemUtil extends YGE {

	protected static String path;
	public static String os = System.getProperty("os.name");
	private static File sys;
	
	/**
	 * Returns the current date
	 * @return String
	 */
	public String getDate() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("MM-dd-yy");
		return new String(ft.format(dNow));
	}
	
	/**
	 * Returns the current time
	 * @return String
	 */
	public String getTime() {
		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
		return new String(ft.format(dNow));
	}
	
	/**
	 * Returns the computer user's information
	 * @return String
	 */
	public String getSysInfo() {
		String user = System.getProperty("user.name");
		String os = System.getProperty("os.name");
		String osversion = System.getProperty("os.version");
		String arch = System.getProperty("os.arch");
		String javav = System.getProperty("java.version");
		return new String("User Name: " + user + "\n"
				+ "OS: " + os + "\n"
				+ "OS Version: " + osversion + "\n"
				+ "Architecture: " + arch + "\n"
				+ "Java Version: " + javav);
	}
	
	/**
	 * Get processor name
	 * @return String
	 */
	public String getProcessor() {
		String proc = "";
		String name = System.getenv("PROCESSOR_IDENTIFIER");
		if (name.contains("Intel")) {
			proc = "Intel";
		} else if (name.contains("Nvidia")) {
			proc = "Nvidia";
		} else if (name.contains("AMD")) {
			proc = "AMD";
		} else  if (name.contains("IBM")) {
			proc = "IBM";
		} else if (name.contains("Motorola")) {
			proc = "Motorola";
		} else {
			proc = name;
		}
		return proc;
	}
	
	/**
	 * Returns the speed of your processor
	 * @return String
	 */
	public String speed() {
		String speed = "Loading...";
		String put = "";
		try {
			BufferedReader read = new BufferedReader(new FileReader(sys));
			String line;
			while ((line = read.readLine()) != null) {
				String[] args = line.split(" ");
				for (int i = 0; i < args.length; i++) {
					if (args[i].contains("@")) {
						String value = args[i + 1];
						String trimmed = value.trim();
						String[] spaces = trimmed.split("");
						for (int j = 0; j < spaces.length; j++) {
							if (!spaces[j].isEmpty() && !spaces[j].trim().isEmpty() && spaces[j] != null) {
								put += spaces[j];
							}
						}
						speed = put;
					}
				}
			}
			read.close();
		} catch (FileNotFoundException e) {
			Debug.pst(e);
		} catch (IOException e) {
			Debug.pst(e);
		}
		return speed;
	}
	
	public void writeData(Class<?> gameClass) throws Exception {
		File info = new File(path + "info.yam");
		if (!info.exists()) {
			info.createNewFile();
		}
		try {
			BufferedWriter a = new BufferedWriter(new FileWriter(info));
			a.write("Last YGEApplication Used=" + gameClass.getName());
			a.newLine();
			a.write("Last YGEApplication Class=" + gameClass.getSimpleName());
			a.close();
		} catch (IOException e) {
			Debug.pst(e);
		}
	}
	
	public void clearCache() {
		File dir = new File(path);
		File[] root = dir.listFiles();
		for (File file : root) {
			file.delete();
		}
	}
	
	/**
	 * Loads all files
	 * @throws Exception
	 */
	public void checkFiles() throws Exception {
		path = getTempDir();
		sys = new File(path + "system.yam");
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdir();
		}
		if (!sys.exists()) {
			if (System.getProperty("os.name").contains("Windows")) {
				Runtime.getRuntime().exec("cmd /c wmic cpu > \"" + sys.getPath() + "\"");
			} else if (System.getProperty("os.name").contains("Mac")) {
				Runtime.getRuntime().exec("sysctl -n machdep.cpu.brand_string > " + sys.getPath());
			}
		}
		
	}
	
	/**
	 * Returns the current YGE version
	 * @return
	 */
	public String getYGEVersion() {
		return YGE_VERSION;
	}
	
	public static String getTempDir() {
		String path = "";
		if (System.getProperty("os.name").contains("Windows")) {
			path = System.getenv("TEMP") + "/Yamanu Cache/";
		} else if (System.getProperty("os.name").contains("Mac")) {
			path = System.getenv("TMPDIR") + "/Yamanu Cache/";
		} else if (System.getProperty("os.name").contains("Linux")) {
			path = "/tmp/Yamanu Cache/";
		}
		return path;
	}
	
}
