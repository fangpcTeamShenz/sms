package com.pj.core.util;

import org.apache.log4j.Logger;

public class Logs {

	private static final boolean enabled = true;
	private static boolean showLocSrc = true;

	private static int level = 1;
	private static final int info = 1;
	private static final int debug = 2;
	private static final int warn = 3;
	private static final int error = 4;
	private static final String msgSplit = ":";
	private static final String thisClassName = Logs.class.getName();

	private static Logger logger = Logger.getLogger("");

	private static final String FS = System.getProperty("file.separator");

	public static void main(String[] args) {
		try {
			System.out.println(2 / 0);
		} catch (Exception e) {
			error("ds", e);
		}
	}

	private static void log(int level, Object message, StackTraceElement[] ste) {
		if (ste != null) {
			message = getStackMsg(ste) + ":" + message;
		}

		switch (level) {
		case 1:
			logger.info(message);
			break;
		case 2:
			logger.debug(message);
			break;
		case 3:
			logger.warn(message);
			break;
		case 4:
			logger.error(message);
			break;
		default:
			logger.debug(message);
		}
	}

	private static String getStackMsg(StackTraceElement[] ste) {
		if (ste == null) {
			return null;
		}
		boolean srcFlag = false;
		for (int i = 0; i < ste.length; i++) {
			StackTraceElement s = ste[i];

			if (srcFlag) {
				return s == null ? "" : s.toString();
			}

			if (thisClassName.equals(s.getClassName())) {
				srcFlag = true;
			}
		}
		return null;
	}

	public static void info(Object message) {
		if (1 < level) {
			return;
		}
		if (showLocSrc) {
			log(1, message, Thread.currentThread().getStackTrace());
		} else {
			log(1, message, null);
		}
	}

	public static void debug(Object message) {
		if (2 < level)
			return;
		if (showLocSrc) {
			log(2, message, Thread.currentThread().getStackTrace());
		} else {
			log(2, message, null);
		}
	}

	public static void warn(Object message) {
		if (3 < level)
			return;
		if (showLocSrc) {
			log(3, message, Thread.currentThread().getStackTrace());
		} else {
			log(3, message, null);
		}
	}

	public static void error(Object message) {
		if (4 < level) {
			return;
		}
		if (showLocSrc) {
			log(4, message, Thread.currentThread().getStackTrace());
		} else {
			log(4, message, null);
		}
	}

	public static void error(Object message, Exception e) {
		if (4 < level) {
			return;
		}
		if (showLocSrc) {
			logger.error(message, e);
		} else {
			log(4, message, null);
		}
	}

	public static Logger getLogger() {
		return logger;
	}

}
