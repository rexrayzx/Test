package com.eezy.logintest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eezy.generics.BaseTest;
import com.eezy.pages.LogInToeezyPage;
import com.eezy.pages.LoginHomePage;
import com.eezy.pages.MoreSignUpoptionPage;
import com.eezy.pages.SignUpForeezyPage;
import com.eezy.pages.UserProfilePage;
import com.eezy.pages.WelcomeToeezyPage;

public class StartNewPlanTest extends BaseTest{
	@Test
	public void startNewPlan() throws Throwable {
		WelcomeToeezyPage setup=new WelcomeToeezyPage(driver);
		setup.getLoginBtn(driver).click();
		SignUpForeezyPage singin=new SignUpForeezyPage(driver);
		singin.getMoreSignUpoptnLink(driver).click();
		MoreSignUpoptionPage option=new MoreSignUpoptionPage(driver);
		option.getEmailSignUpoptnLink(driver).click();
		LogInToeezyPage login=new LogInToeezyPage(driver);
		login.getLoginEmailtxb().sendKeys(file.getDataFromPropertFile("email"));
		login.getLoginPasswordtxb().sendKeys(file.getDataFromPropertFile("password"));
		login.getContinueBtn().click();
		UserProfilePage profile=new UserProfilePage(driver);
		LoginHomePage loginHome=new LoginHomePage(driver);
		pageutil.waitToDisplay(driver, loginHome.getSkipbtn());
		pageutil.tap(driver, .1, .1);
		profile.getYesBtn().click();
		Assert.assertTrue(loginHome.getInspireMeBtn().isDisplayed());
	}
}
