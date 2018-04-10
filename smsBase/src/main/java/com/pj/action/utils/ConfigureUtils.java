package com.pj.action.utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 读取配置 后期可以考虑从共享缓存读取
 */
public class ConfigureUtils {

	static Properties config = null;
	
	static {
		config = new Properties();
		try {
			InputStream in = ClassLoader.class.getResourceAsStream("/configure.properties");
			InputStreamReader ini = new InputStreamReader(in, "utf-8");
			config.load(ini); /// 加载属性列表
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
    /** 
    * 读取属性文件中相应键的值 
    * @param key 
    *            主键 
    * @return String 
    */ 
    public static String getProperty(String key) { 
        return config.getProperty(key); 
    } 
	
}
