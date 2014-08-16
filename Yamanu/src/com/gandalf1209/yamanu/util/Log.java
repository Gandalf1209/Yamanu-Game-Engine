package com.gandalf1209.yamanu.util;

/**
 * 
 * @author Gandalf1209
 *
 */
public class Log {

	/**
	 * Outputs INFO message to log
	 * @param out Output
	 */
	public static void out(String out) {
		System.out.println("[INFO] " + out);
	}
	
	/**
	 * Outputs WARNING message to log
	 * @param out Output
	 */
	public static void warn(String warn) {
		System.out.println("[WARNING] " + warn);
	}
	
	/**
	 * Outputs ERROR message to log
	 * @param out Output
	 */
	public static void err(String err) {
		System.out.println("[ERROR] " + err);
	}
	
	/**
	 * Outputs SEVERE message to log
	 * @param out Output
	 */
	public static void sev(String sev) {
		System.out.println("[SEVERE] " + sev);
	}
	
}
