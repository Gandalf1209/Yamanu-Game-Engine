package com.gandalf1209.yamanu.util;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.gandalf1209.yamanu.game.GameObject;
import com.gandalf1209.yamanu.graphics.GraphicsHandler;
import com.gandalf1209.yamanu.handlers.JVMRuntimeHandler;
import com.gandalf1209.yamanu.yge.Cache;

public class Debug {
	
	private static SystemUtil util = new SystemUtil();
	private static Class<?> c;
	private static JVMRuntimeHandler runtime = new JVMRuntimeHandler();
	
	private static Thread debug;

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
	
	public static void initDebugThread() {
		debug = new Thread() {
			public void run() {
				JFrame frame = new JFrame("Debug Information");
				JPanel panel = new JPanel();
				
				panel.setLayout(new GridLayout(10, 1));
				JLabel objects = new JLabel();
				panel.add(objects);
				JLabel lights = new JLabel();
				panel.add(lights);
				JLabel threads = new JLabel();
				panel.add(threads);
				JLabel mem = new JLabel();
				panel.add(mem);
				
				frame.setSize(300, 400);
				frame.setResizable(false);
				frame.setContentPane(panel);
				frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				frame.setVisible(true);
				while (true) {
					objects.setText("GameObject count: " + GameObject.getCount());
					lights.setText("Lights count: " + GraphicsHandler.getlCount());
					try {
						runtime.getThreads();
						threads.setText("Threads: " + runtime.getThreadCount());
					} catch (Exception e) {
					}
					mem.setText("Free Memory: " + runtime.freeMem() + " MB");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
	}
	
	public static void showDetails(boolean value) {
		initDebugThread();
		if (value) {
			debug.start();
		} else {
			try {
				debug.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
