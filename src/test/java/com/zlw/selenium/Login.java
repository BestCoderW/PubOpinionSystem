package com.zlw.selenium;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Login {

	public WebDriver driver;
	
	/**
	 * 初始化driver
	 */
	public void InitDriver() {
		
		//配置驱动
		System.setProperty("webdriver.chrome.driver", "E:\\Developsoftware\\WebDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		//获取慕课网的url
		driver.get("http://www.imooc.com");
		//窗口最大化
		driver.manage().window().maximize();
		driver.findElement(By.id("js-signin-btn")).click();
		
	}
	
	/**
	 * 登录脚本
	 * @throws InterruptedException 
	 */
	public void LoginScript(String username,String userpwd) throws InterruptedException {
		
		this.InitDriver();
		
//		String username = "1104031082@qq.com";
//		String userpwd = "zlw240817";
//		String userElement = "email";
//		String passwordElement = "password";
//		String loginbuttonElement = "moco-btn";
//		String headerElement = "header-avator";
//		String nameElement = "name";
//		String userby = "name";
//		String passwordby = "name";
//		String loginbuttonby = "className";
//		String headerby = "id";
//		String userinfoby = "className";
		
		//隐式等待
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(5000);
		WebElement user = this.element(byStr("username"));
		user.isDisplayed();
		WebElement password = this.element(byStr("userpwd"));
		password.isDisplayed();
		WebElement loginButton = this.element(byStr("loginbutton"));
		loginButton.isDisplayed();
		user.sendKeys(username);
		password.sendKeys(userpwd);
		loginButton.click();
		Thread.sleep(3000);
		
		WebElement header = this.element(byStr("header"));
		header.isDisplayed();
		Actions actions = new Actions(driver);
		actions.moveToElement(header).perform();
		String userinfo = this.element(byStr("userinfo")).getText();
		if (userinfo.equals("慕容7141666")) {
			this.takeScreenShot();
			System.out.println("登录成功");
		} else {
			System.out.println("登录失败");
		}
		driver.close();
	}
	
	/**
	 * 封装By by
	 * @param by
	 * @param local
	 * @return
	 */
	public By byStr(String username) {
		
		ProUtil properties = new ProUtil("element.properties");
		String locator = properties.getPro(username);
		String locatorType = locator.split(">")[0];
		String locatorValue = locator.split(">")[1];
		
		if (locatorType.equals("id")) {
			return By.id(locatorValue);
		} else if (locatorType.equals("name")) {
			return By.name(locatorValue);
		} else if (locatorType.equals("className")) {
			return By.className(locatorValue);
		} else {
			return By.xpath(locatorValue);
		}
	}
	
	/**
	 * 封装Element
	 * @param by
	 * @return
	 */
	public WebElement element(By by) {
		WebElement ele = driver.findElement(by);
		return ele;
	}
	
	/**
	 * 截屏
	 */
	public void takeScreenShot() {
		//获取当前时间，将当前时间作为文件名
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
		String path = dateformat.format(System.currentTimeMillis());
		path = path + ".png";
		//截屏文件的路径设置
		String curPath = System.getProperty("user.dir");
		String screenPath = curPath + "/jpg/" + path;
		File screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screen,new File(screenPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws InterruptedException {

		Login login = new Login();
//		login.LoginScript("1104031082@qq.com","zlw240817");
		
		/**
		 * key-value
		 * username-password
		 */
		HashMap<String, String> user = new HashMap<String, String>();
		user.put("1104031082@qq.com", "zlw240817");
		Iterator us = user.entrySet().iterator();
		while (us.hasNext()) {
			Entry entry = (Entry) us.next();
			String username = entry.getKey().toString();
			String password = entry.getValue().toString();
			login.LoginScript(username, password);
		}
		
	}

}
