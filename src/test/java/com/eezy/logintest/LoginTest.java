package com.eezy.logintest;

import java.util.List;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.eezy.generics.BaseTest;
import com.eezy.pages.LoginHomePage;

/**
 * 
 * @author Rajiv
 *
 */
public class LoginTest extends BaseTest{
	@Test
	public void login() throws Throwable {
		loginToApp(file.getDataFromPropertFile("email"), file.getDataFromPropertFile("password"));
		LoginHomePage loginHome=new LoginHomePage(driver);
		try{
			loginHome.getLaterBtn().click();
		}catch (Exception e) {
			// TODO: handle exception
		}
		pageutil.scroll(driver, .5, .5, .3, .8);
		WebDriverWait wait=new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.visibilityOf(loginHome.getTemperatureTxt()));
		Assert.assertTrue(loginHome.getTemperatureTxt().isDisplayed());

	}
}
