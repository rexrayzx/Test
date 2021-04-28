package com.eezy.logintest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eezy.generics.BaseTest;
import com.eezy.pages.DrawerMenuPage;
import com.eezy.pages.LoginHomePage;

import com.eezy.pages.WelcomeToeezyPage;
/**
 * 
 * @author Rajiv
 *
 */
public class LogOutTest extends BaseTest{
	@Test
	public void logout() throws Throwable {
		WelcomeToeezyPage setup=new WelcomeToeezyPage(driver);
		loginToApp(file.getDataFromPropertFile("email"), file.getDataFromPropertFile("password"));
		LoginHomePage loginHome=new LoginHomePage(driver);
		pageutil.waitToDisplay(driver, loginHome.getSkipbtn());
		pageutil.tap(driver, .5, .1);
		DrawerMenuPage menu=new DrawerMenuPage(driver);
		menu.getLogoutMenu().click();
		menu.getYesLogoutBtn().click();
		Assert.assertTrue(setup.getWizardtitleText(driver).isDisplayed());
	}

}
