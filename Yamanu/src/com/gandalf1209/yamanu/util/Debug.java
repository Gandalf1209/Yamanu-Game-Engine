package com.gandalf1209.yamanu.util;

import com.gandalf1209.yamanu.yge.Cache;

public class Debug {
	
	private static SystemUtil util = new SystemUtil();
	private static Class<?> c;

	public static void initClass(Class<?> c) {
		Debug.c = c;
	}
	
	/**
	 * Shorter way of printing the stack trace for an exception
	 * @param e Exception
	 */
	public static void pst(Exception e) {
		System.out.println("[DEBUG: Error] " + e.getMessage() + ", Cause: " + e.getCause());
		Cache.forceCreateCacheFile("crash_log_" + util.getTime() + ".txt");
		Cache.writeToCacheFile("crash_log_" + util.getTime() + ".txt", "Crash in application: " + c + "\n"
				+ "Crash Message: " + e.getMessage() + ", Cause: " + e.getCause());
		e.printStackTrace();
	}
	
	/**
	 * Print information to the log with defaulted debug info
	 * @param out String to log
	 * @param c Class (If non-static, do this.getClass())
	 */
	public static void out(String out, Class<?> c) {
		System.out.println("[DEBUG: " + c.getSimpleName() + "] " + out);
	}
	
	/**
	 * Returns the line where the method, the getMethodCallLine() method is in, is called.
	 * @return Integer
	 */
	public static int getMethodCallLine() {
		return Thread.currentThread().getStackTrace()[3].getLineNumber();
	}
	
}
