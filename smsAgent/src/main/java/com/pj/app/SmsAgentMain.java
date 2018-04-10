package com.pj.app;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.pj.app.server.netty.HttpServer;

/**
 * 接口中心
 * 
 * @author Fangpc
 *
 */
public class SmsAgentMain {
	
	private static int host = 8090;//  接入2
//	private static int host = 8089;//  接入1
	
	private static Log log = LogFactory.getLog(SmsAgentMain.class);

	public static void main(String[] args) throws Exception {
		try {
			HttpServer server = new HttpServer();
			log.info("Http Server listening on " + host + " ...");
			server.start(host);
		} catch (Exception e) {
			log.error("服务启动失败", e);
			System.exit(0);// 程序GG
		}
	}
}
