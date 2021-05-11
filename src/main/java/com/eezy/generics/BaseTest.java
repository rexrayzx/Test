package com.eezy.generics;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.eezy.pages.LogInToeezyPage;
import com.eezy.pages.MoreSignUpoptionPage;
import com.eezy.pages.SignUpForeezyPage;
import com.eezy.pages.WelcomeToeezyPage;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.screenrecording.BaseStartScreenRecordingOptions;
import io.appium.java_client.screenrecording.CanRecordScreen;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.AndroidServerFlag;
/**
 * 
 * @author Rajiv
 *
 */
public class BaseTest {
	//object creation of utility classes
	public AndroidDriver driver;
	public static AndroidDriver staticDriver;
	public AppiumDriverLocalService service;
	public FileUtil file=new FileUtil();
	public JavaUtil util=new JavaUtil();
	public ExcelLibrary excelLib=new ExcelLibrary();
	public GenericUtil pageutil=new GenericUtil();
	String date=new JavaUtil().currentdate().replace(" ", "_").replace(":", "_");
	
	
	/*@BeforeSuite
	public void configBS() {
		String date=new JavaUtil().currentdate().replace(" ", "_").replace(":", "_");
		service=AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
				.usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
				.usingPort(4723)
				.withAppiumJS(new File("C:\\Users\\Rajiv\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withLogFile(new File(PathConstant.AppiumLogpath+"/appiumlog.txt"+date)));
		service.start();
	}*/
	
	
	/**
	 * To start Appium server
	 */
	
	/*@BeforeSuite(groups = {"Smoke","Regression"})
	public void configBS() {
		service=AppiumDriverLocalService.buildDefaultService();
		service.start();
	}*/
	/**
	 * To start the Appium server
	 * @throws Exception 
	 */
	/*@BeforeSuite
	public void configBS() throws Exception {
		String IP_ADDRESS = "127.0.0.1";
		String bootStrapPort;
		String chromePort;
		int port;
		port = getPort();
		bootStrapPort = Integer.toString(getPort());
		chromePort = Integer.toString(getPort());
		service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder().withAppiumJS(new File(getJSPath()))
		.usingDriverExecutable(new File(getNodePath())).withIPAddress(getIP()).usingPort(port)
		.withArgument(AndroidServerFlag.BOOTSTRAP_PORT_NUMBER, bootStrapPort)
		.withArgument(AndroidServerFlag.CHROME_DRIVER_PORT, chromePort));
		service.start();
	}*/
	
	
	/**
	 * To launch the application
	 * @throws Throwable
	 */
	@BeforeMethod(groups = {"Smoke","Regression"})
	public void beforeMethod() throws Throwable {
		/*String deviceName=System.getProperty("deviceName");
		String platformVersion=System.getProperty("platformVersion");
		String UDID=System.getProperty("UDID");*/
		
		if(file.getDataFromPropertFile("enviroment").equalsIgnoreCase("realDevice")) {
		DesiredCapabilities cap=new DesiredCapabilities();
		cap.setCapability("app", "./App//eezy-uat-2.apk");
		cap.setCapability("deviceName", file.getDataFromPropertFile("deviceName"));
		cap.setCapability("platformVersion", file.getDataFromPropertFile("platformVersion"));
		cap.setCapability("appActivity", file.getDataFromPropertFile("appActivity"));
		cap.setCapability("automationName", file.getDataFromPropertFile("automationName"));
		cap.setCapability("platformName", file.getDataFromPropertFile("platformName"));
		cap.setCapability("appPackage", file.getDataFromPropertFile("appPackage"));
		cap.setCapability("UDID", file.getDataFromPropertFile("UDID"));
		cap.setCapability("autoGrantPermissions", true);
		//cap.setCapability("noReset", true);
		URL url=new URL(file.getDataFromPropertFile("url"));
		
		driver=new AndroidDriver (url,cap);
		staticDriver=driver;
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		}else{
			DesiredCapabilities caps = new DesiredCapabilities();
			caps.setCapability("appActivity", file.getDataFromPropertFile("appActivity"));
			caps.setCapability("automationName", file.getDataFromPropertFile("automationName"));
			caps.setCapability("appPackage", file.getDataFromPropertFile("appPackage"));
			caps.setCapability("app", "./App//eezy-uat-2.apk");
			caps.setCapability("autoGrantPermissions", true);
		        driver = new AndroidDriver(service.getUrl(), caps);
		        staticDriver=driver;
		        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			}
	}
	
	/**
	 * To close the application
	 * @throws Exception
	 */
	@AfterMethod(groups = {"Smoke","Regression"})
	public void afterMethod() throws Exception {
		driver.quit();
	}
	
	/**
	 * To Stop the server
	 */
	@AfterSuite(groups = {"Smoke","Regression"})
	public void configAS() {
		service.stop();
	}
	
	/**
	 * To login to application
	 * @param email
	 * @param password
	 */
	public void loginToApp(String email,String password) {
		WelcomeToeezyPage setup=new WelcomeToeezyPage(driver);
		setup.getLoginBtn(driver).click();
		SignUpForeezyPage singin=new SignUpForeezyPage(driver);
		singin.getMoreSignUpoptnLink(driver).click();
		MoreSignUpoptionPage option=new MoreSignUpoptionPage(driver);
		option.getEmailSignUpoptnLink(driver).click();
		LogInToeezyPage login=new LogInToeezyPage(driver);
		login.getLoginEmailtxb().sendKeys(email);
		login.getLoginPasswordtxb().sendKeys(password);
		login.getContinueBtn().click();
	}
	
	
	private static int getPort() throws Exception {
		int port = 0;
		try {
		ServerSocket socket = new ServerSocket(0);
		socket.setReuseAddress(true);
		port = socket.getLocalPort();
		socket.close();
		} catch (Exception e) {
		e.printStackTrace();
		}
		return port;
		}
	
	private static String getNodePath() throws IOException, InterruptedException {
		String jsPaths = null;
		String nodePath = null;
		Process p;
		BufferedReader reader;
		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.contains("Win")) {
		String whereAppium = "where" + " " + "node";
		p = Runtime.getRuntime().exec(whereAppium);
		reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((jsPaths = reader.readLine()) != null) {
		nodePath = jsPaths;
		break;
		}
		p.waitFor();
		p.destroy();
		if (nodePath == null) {
		System.exit(0);
		}
		} else {
		String command = "which " + "node";
		p = Runtime.getRuntime().exec(command);
		p.waitFor();
		reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line = "";
		while ((line = reader.readLine()) != null) {
		nodePath = line;
		break;
		}
		p.destroy();
		if (nodePath == null) {
		System.exit(0);
		}
		}
		return nodePath;
		}
	
	private static String getJSPath() throws IOException, InterruptedException {
		String jsPaths = null;
		String actualJSPath = null;
		String operatingSystem = System.getProperty("os.name");
		if (operatingSystem.contains("Win")) {
		String whereAppium = "where" + " " + "appium";
		Process p = Runtime.getRuntime().exec(whereAppium);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
		while ((jsPaths = stdInput.readLine()) != null) {
		actualJSPath = jsPaths.replace("appium", "node_modules\\appium\\build\\lib\\main.js");
		break;
		}
		p.waitFor();
		p.destroy();
		if (actualJSPath == null) {
		System.exit(0);
		}
		} else {
		actualJSPath = "//usr//local//lib//node_modules//appium//build//lib//main.js";
		}
		return actualJSPath;
		}
	private static String getIP() throws UnknownHostException { 
		InetAddress addr = InetAddress.getLocalHost();
		return addr.getHostAddress();
	}
}
