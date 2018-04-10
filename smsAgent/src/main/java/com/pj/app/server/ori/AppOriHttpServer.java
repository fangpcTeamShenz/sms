package com.pj.app.server.ori;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.aliyun.openservices.shade.com.alibaba.rocketmq.shade.com.alibaba.fastjson.JSON;
import com.pj.app.service.OrderService;
import com.pj.core.model.Result;
import com.pj.core.util.threadPool.ThreadPoolProxyFactory;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

/**
 * bio访问webServer http://localhost:7089/ori
 * 
 * @author Fangpc
 * 
 */
public class AppOriHttpServer {
	private static Log log = LogFactory.getLog(AppOriHttpServer.class);
	private static int host = 7089;

	private void start() {
		ThreadPoolProxyFactory.createNormalPoolProxy().execute(new Runnable() {
			public void run() {
				try {
					// 实现HTTP SERVER
					HttpServer hs = HttpServer.create(new InetSocketAddress(
							host), 0);// 设置HttpServer的端口为8091
					hs.createContext("/ori", new MyHandler());// 用MyHandler类内处理到/的请求
					hs.setExecutor(null); // creates a default executor
					log.info("Http Server listening on " + host + " ...");
					hs.start();
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		});
	}

	public static void main(String[] args) {
		AppOriHttpServer httpServer = new AppOriHttpServer();
		httpServer.start();
	}
}

class MyHandler implements HttpHandler {
	public void handle(HttpExchange t) throws IOException {
		InputStream is = t.getRequestBody();
		byte[] readByte = new byte[1024];
		int i = 0;
		String readData = "";
		while ((i = is.read(readByte)) != -1) {
			readData += new String(readByte, 0, i, "UTF-8");
		}
		Result result = OrderService.receive(readData, t.getRemoteAddress()
				.getHostString());
		String response = JSON.toJSONString(result);
		t.sendResponseHeaders(200, response.getBytes().length);
		OutputStream os = t.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}
}