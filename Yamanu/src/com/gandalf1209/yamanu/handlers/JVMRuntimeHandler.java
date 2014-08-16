package com.gandalf1209.yamanu.handlers;

import java.util.Set;

import com.gandalf1209.yamanu.util.Debug;
import com.gandalf1209.yamanu.util.SystemUtil;

/**
 * 
 * @author Gandalf1209
 *
 */
public class JVMRuntimeHandler {
	
	private static Thread exit;
	private static SystemUtil util = new SystemUtil();

	/**
	 * Returns free JVM Memory
	 * @return Long
	 */
	public long freeMem() {
		return (Runtime.getRuntime().freeMemory() / 1024 / 1024);
	}
	
	/**
	 * Returns total JVM Memory
	 * @return Long
	 */
	public long totalMem() {
		return (Runtime.getRuntime().totalMemory() / 1024 / 1024);
	}
	
	/**
	 * Returns max JVM Memory
	 * @return Long
	 */
	public long maxMem() {
		return (Runtime.getRuntime().maxMemory() / 1024 / 1024);
	}
	
	/**
	 * Cleans up any unused data, allowing more free memory
	 */
	public void cleanup() {
		if (totalMem() < 30) {
			System.gc();
		}
	}
	
	/**
	 * Correctly stops a YGEApplication
	 */
	public void exit(final Class<?> gameClass) {
		exit = new Thread("YGE Exit Thread") {
			boolean running = true;
			public void run() {
				while (running) {
					try {
						util.writeData(gameClass);
					} catch (Exception e) {
						Debug.pst(e);
					}
					System.out.println("Finished with " + freeMem() + " MB");
					System.out.println("Yamanu: " + util.getYGEVersion());
					running = false;
				}
			}
		};
		Runtime.getRuntime().addShutdownHook(exit);
		Runtime.getRuntime().runFinalization();
		try {
			stopThreads();
		} catch (InterruptedException e) {
			Debug.pst(e);
		}
		Runtime.getRuntime().exit(0);
	}
	
	/**
	 * returns Available Processors
	 * @return Integer
	 */
	public int getProc() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	private static void stopThreads() throws InterruptedException {
		Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
		Thread[] threads = threadSet.toArray(new Thread[threadSet.size()]);
		for (Thread thread : threads) {
			if (!thread.isDaemon()) {
				thread.interrupt();
			}
		}
	}
	
}
