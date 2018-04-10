package com.pj.app.utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {

	private String driver;
	private String database;
	private String user;
	private String password;
	private String server;
	private String port1;
	private String port2;
	private String portHttp;
	private String portNoSign;
	private String tempFiles;
	private String authorityRecordedOrder;
	private String authorityCompanyInfo;
	private String tempFilesUrl = "/assets/tempFiles/";

	public ReadConfig() {
		String path = this.getClass().getResource("/").getPath();

		path = path.replaceAll("%20", " ");

		//String propfile = path + "\\com\\config\\config.ini";
		String propfile = path + "config.ini";
		
		Properties p = new Properties();
		try {
			p.load(new FileInputStream(propfile));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Read values from Properties file
		this.driver = p.getProperty("driver");
		this.database = p.getProperty("database");
		this.user = p.getProperty("user");
		this.password = p.getProperty("password");
		this.server = p.getProperty("server");
		this.port1 = p.getProperty("port1");
		this.port2 = p.getProperty("port2");
		this.portHttp = p.getProperty("portHttp");
		this.portNoSign = p.getProperty("portNoSign");
		this.authorityRecordedOrder = p.getProperty("authorityRecordedOrder");
		this.tempFiles = p.getProperty("tempFiles");
		this.authorityCompanyInfo = p.getProperty("authorityCompanyInfo");
		
	}

	public String getPortHttp() {
		return portHttp;
	}

	public String getServer() {
		return this.server;
	}

	public String getPort1() {
		return this.port1;
	}

	public String getPort2() {
		return this.port2;
	}

	public String getDriver() {
		return this.driver;
	}

	public String getDatabase() {
		return this.database;
	}

	public String getPortNoSign() {
		return this.portNoSign;
	}

	public String getPassword() {
		String psword = null;
		try {
			psword = new DesUtils().deEncrypt(this.password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return psword;
	}

	public String getUser() {
		String userid = null;
		try {
			userid =  new DesUtils().deEncrypt(this.user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userid;
	}

	public String getTempFiles(String realPath) {
		return realPath+tempFilesUrl;
	}

	public String getAuthorityRecordedOrder() {
		return authorityRecordedOrder;
	}

	public String getAuthorityCompanyInfo() {
		return authorityCompanyInfo;
	}
	
	
}
