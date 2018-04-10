package com.pj.app.utils;

import com.pj.core.util.Base64Util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 
 * @author qsl
 * 
 */
public class SocketUtil {

	private static final String CHARACTER = "gbk";
	Log log = LogFactory.getLog(getClass().getName());
	
	public String getSocketMessageOldd(String url,String server,int PORT) throws IOException {
		Socket socket = null;
		PrintWriter outw = null;
		InputStream dataIn = null;
		String sockResultStr = null;
		String naseIns = Base64Util.encode(url);
		boolean logtag = true;
		StringBuffer readData = new StringBuffer();
		try {
			if(url.contains("Action=GetBalance")||url.contains("Action=ChangePassword&AgentAccount=")){
				logtag = false;
			}
			if(logtag){
			log.debug( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil Starting.....");
			}
			socket = new Socket(); // 实例化socket
			SocketAddress socketAddress = new InetSocketAddress(server,
					PORT); // 获取sockaddress对象
			socket.connect(socketAddress,90000); // 连接socket并设置连接超时为90秒，如果5秒后服务端还没有响应，则弹出异常信息。
			socket.setSoTimeout(90000);
		
			outw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), "GBK")), true);	
			naseIns = naseIns.replace("\\r\\n", "");
			if(logtag){
				
					log.debug("Socket_send:" + url);
			
			}
			outw.println(naseIns);
			dataIn = socket.getInputStream();
			byte[] readByte = new byte[1024];
			int i = 0;
			socket.shutdownOutput();
			Thread.sleep(500);
			socket.setReceiveBufferSize(128*1024);
			while ((i = dataIn.read(readByte)) != -1) {
				String bufStr = new String(readByte, 0, i, "gbk");
				System.out.println(bufStr);
				readData.append(bufStr);
//				readData += new String(readByte, 0, i, "gbk");
				//System.out.println("["+i+"]:"+readData);
				if (readData.indexOf("\r")>-1 || readData.indexOf("\n")>-1)// || i<1024
//				if (readData.contains("\r") || readData.contains("\n")||i<1024)
					break;
			}
			// 创建文件对象
//        	File fileText = new File("D:\\ItemSoftware\\abc\\"+"456.txt");
//        	fileText.deleteOnExit() ; //如果存在则删除
//        	fileText.createNewFile() ;//创建新文件
//        	OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileText),"gbk");      
//            BufferedWriter writer=new BufferedWriter(write);          
//            writer.write(readData.toString());      
//            writer.close();  
            
			sockResultStr = new String(Base64Util.decode(readData.toString()), CHARACTER);
			if(logtag){
			log.debug("Socket_return:" + sockResultStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sockResultStr = "fail";
		} finally {
			try {
				if (dataIn != null) {
					dataIn.close();
					dataIn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (outw != null) {
					outw.close();
					outw = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socket.close();
					socket = null;
					if(logtag){
					log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil finished.....");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sockResultStr;
		}
	}

	public String getSocketMessageOld(String url,String server,int PORT) throws IOException {
		Socket socket = null;
		PrintWriter outw = null;
		DataInputStream dataIn = null;
		String sockResultStr = null;
		String naseIns = Base64Util.encode(url);
		boolean logtag = true;
//		String readData = "";
		StringBuffer readData = new StringBuffer();
		try {
			if(url.contains("Action=GetBalance")||url.contains("Action=ChangePassword&AgentAccount=")){
				logtag = false;
			}
			if(logtag){
			log.debug( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil Starting.....");
			}
			socket = new Socket(); // 实例化socket
			SocketAddress socketAddress = new InetSocketAddress(server,
					PORT); // 获取sockaddress对象
			socket.connect(socketAddress,90000); // 连接socket并设置连接超时为90秒，如果5秒后服务端还没有响应，则弹出异常信息。
			socket.setSoTimeout(90000);
		
			outw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "GBK")), true);	
			naseIns = naseIns.replace("\\r\\n", "");
			if(logtag){
				
					log.debug("Socket_send:" + url);
			
			}
			outw.println(naseIns);
			dataIn = new DataInputStream(socket.getInputStream());
//			byte[] readByte = new byte[1024];
			byte[] readByte = new byte[2048];
			int i = 0;
			Thread.sleep(10000);
			socket.setReceiveBufferSize(128*1024);
			while ((i = dataIn.read(readByte)) != -1) {
				String bufStr = new String(readByte, 0, i, "gbk");
				System.out.println(bufStr);
				readData.append(bufStr);
//				readData += new String(readByte, 0, i, "gbk");
				//System.out.println("["+i+"]:"+readData);
				if (readData.indexOf("\r")>-1 || readData.indexOf("\n")>-1)// || i<1024
//				if (readData.contains("\r") || readData.contains("\n")||i<1024)
					break;
			}
			// 创建文件对象
//        	File fileText = new File("D:\\ItemSoftware\\abc\\"+"456.txt");
//        	fileText.deleteOnExit() ; //如果存在则删除
//        	fileText.createNewFile() ;//创建新文件
//        	OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(fileText),"gbk");      
//            BufferedWriter writer=new BufferedWriter(write);          
//            writer.write(readData.toString());      
//            writer.close();  
            
			sockResultStr = new String(Base64Util.decode(readData.toString()), CHARACTER);
			if(logtag){
			log.debug("Socket_return:" + sockResultStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sockResultStr = "fail";
		} finally {
			try {
				if (dataIn != null) {
					dataIn.close();
					dataIn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (outw != null) {
					outw.close();
					outw = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socket.close();
					socket = null;
					if(logtag){
					log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil finished.....");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sockResultStr;
		}
	}
	
	@SuppressWarnings("finally")
	public String getSocketMessage(String url,String server,int PORT) throws IOException {
		Socket socket = null;
		PrintWriter outw = null;
		DataInputStream dataIn = null;
		String sockResultStr = null;
		String naseIns = Base64Util.encode(url);
		boolean logtag = true;
		String readData = "";
		try {
			if(url.contains("Action=GetBalance")||url.contains("Action=ChangePassword&AgentAccount=")){
				logtag = false;
			}
			if(logtag){
			log.debug( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil Starting.....");
			}
			socket = new Socket(); // 实例化socket
			SocketAddress socketAddress = new InetSocketAddress(server,
					PORT); // 获取sockaddress对象
			socket.connect(socketAddress,30000); // 连接socket并设置连接超时为5秒，如果5秒后服务端还没有响应，则弹出异常信息。
			socket.setSoTimeout(30000);
			outw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "GBK")), true);	
			naseIns = naseIns.replace("\\r\\n", "");
			if(logtag){
				
					log.debug("Socket_send:" + url);
			
			}
			outw.println(naseIns);
			dataIn = new DataInputStream(socket.getInputStream());
			byte[] readByte = new byte[1024];
			int i = 0;
			while ((i = dataIn.read(readByte)) != -1) {
				readData += new String(readByte, 0, i, "gbk");
				//System.out.println("["+i+"]:"+readData);
				if (readData.contains("\r") || readData.contains("\n")||i<1024)
					break;
			}
			
			sockResultStr = new String(Base64Util.decode(readData), CHARACTER);
			if(logtag){
			log.debug("Socket_return:" + sockResultStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sockResultStr = "fail";
		} finally {
			try {
				if (dataIn != null) {
					dataIn.close();
					dataIn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (outw != null) {
					outw.close();
					outw = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socket.close();
					socket = null;
					if(logtag){
					log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil finished.....");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sockResultStr;
		}
	}
	
	public String getSocketMessageByReader(String url,String server,int PORT) throws IOException {
		Socket socket = null;
		PrintWriter outw = null;
		BufferedReader dataIn = null;
		String sockResultStr = null;
		String naseIns = Base64Util.encode(url);
		boolean logtag = true;
//		String readData = "";
		StringBuffer readData = new StringBuffer();
		try {
			if(url.contains("Action=GetBalance")||url.contains("Action=ChangePassword&AgentAccount=")){
				logtag = false;
			}
			if(logtag){
			log.debug( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil Starting.....");
			}
			socket = new Socket(); // 实例化socket
			SocketAddress socketAddress = new InetSocketAddress(server,
					PORT); // 获取sockaddress对象
			socket.connect(socketAddress,90000); // 连接socket并设置连接超时为5秒，如果5秒后服务端还没有响应，则弹出异常信息。
			socket.setSoTimeout(90000);
			outw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "GBK")), true);	
			naseIns = naseIns.replace("\\r\\n", "");
			if(logtag){
				
					log.debug("Socket_send:" + url);
			
			}
			outw.println(naseIns);
			dataIn =  new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String s;
			Thread.sleep(10000);
			while ((s = dataIn.readLine()) != null) {
				readData.append(s);
				System.out.println(s);
				if (readData.indexOf("\r")>-1 || readData.indexOf("\n")>-1)
//					if (readData.contains("\r") || readData.contains("\n")||i<1024)
						break;
			}
			
			sockResultStr = new String(Base64Util.decode(readData.toString()), CHARACTER);
			if(logtag){
			log.debug("Socket_return:" + sockResultStr);
			}
		} catch (Exception e) {
			e.printStackTrace();
			sockResultStr = "fail";
		} finally {
			try {
				if (dataIn != null) {
					dataIn.close();
					dataIn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (outw != null) {
					outw.close();
					outw = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socket.close();
					socket = null;
					if(logtag){
					log.debug(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())+"......SocketUtil finished.....");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sockResultStr;
		}
	}
	
	@SuppressWarnings("finally")
	public String getNoBase64Socket( String url,String server,int PORT) throws IOException {
		Socket socket = null;
		PrintWriter outw = null;
		DataInputStream dataIn = null;
		String sockResultStr = null;
		String naseIns = url;
		try {
			log.debug( "......SocketUtil Starting.....");
			socket = new Socket(); // 实例化socket
			SocketAddress socketAddress = new InetSocketAddress(server,
					PORT); // 获取sockaddress对象
			socket.connect(socketAddress, 30000); // 连接socket并设置连接超时为5秒，如果5秒后服务端还没有响应，则弹出异常信息。
			socket.setSoTimeout(30000);
			outw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream(), "GBK")), true);	
			naseIns = naseIns.replace("\\r\\n", "");
			log.debug("Socket_send:" + url);
			outw.println(naseIns);
			String queryResult = "";
			while (queryResult.equals("")) {
				dataIn = new DataInputStream(socket.getInputStream());
				byte[] readByte = new byte[1024];
				dataIn.read(readByte, 1, 1000);
				String readData = new String(readByte,"GBK").trim();
				queryResult = readData;
			}
			sockResultStr = queryResult;
			log.debug("Socket_return:" + sockResultStr);
		} catch (Exception e) {
			e.printStackTrace();
			sockResultStr = "fail";
		} finally {
			try {
				if (dataIn != null) {
					dataIn.close();
					dataIn = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (outw != null) {
					outw.close();
					outw = null;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (socket != null) {
					socket.close();
					socket = null;
					log.debug("......SocketUtil finished.....");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return sockResultStr;
		}
	}
}
