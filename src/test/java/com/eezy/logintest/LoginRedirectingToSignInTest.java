package com.eezy.logintest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eezy.generics.BaseTest;
import com.eezy.pages.SignUpForeezyPage;
import com.eezy.pages.WelcomeToeezyPage;
/**
 * 
 * @author Rajiv
 *
 */
public class LoginRedirectingToSignInTest extends BaseTest{
	@Test(groups = "Smoke")
	public void loginToSingIn() throws Throwable {
		String expectedText=excelLib.getExcelData("WelcomePage", 1, 2);
		WelcomeToeezyPage welcome=new WelcomeToeezyPage(driver);
		welcome.getLoginBtn(driver).click();
		SignUpForeezyPage signup=new SignUpForeezyPage(driver);
		String actualText = signup.getSignUpMessage(driver).getText();
		Assert.assertEquals(actualText, expectedText);
	}

}
