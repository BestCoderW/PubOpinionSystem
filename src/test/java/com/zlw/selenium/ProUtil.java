package com.zlw.selenium;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class ProUtil {

	private Properties prop;
	private String filePaht;
	
	/**
	 * 构造方法
	 * @param filePath
	 */
	public ProUtil(String filePath) {
		this.filePaht = filePath;
		this.prop = readProperties();
	}
	
	/**
	 * 读取配置文件
	 * @return
	 */
	public Properties readProperties() {
		Properties properties = new Properties();
		try {
			InputStream inputStream = new FileInputStream(filePaht);
			BufferedInputStream in =new BufferedInputStream(inputStream);
			properties.load(in);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return properties;
	}
	
	public String getPro(String key) {
		if (prop.containsKey(key)) {
			String username = prop.getProperty(key);
			return username;
		} else {
			System.out.println("你获取的key值不对");
			return "";
		}
	}

}
