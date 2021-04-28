package com.eezy.logintest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.eezy.generics.BaseTest;
import com.eezy.pages.OTPPage;
import com.eezy.pages.SignUpForeezyPage;
import com.eezy.pages.WelcomeToeezyPage;
/**
 * 
 * @author Rajiv
 *
 */
public class LoginUsingMobileNumberTest extends BaseTest{
	@Test(groups = "Smoke")
	public void loginUsingMobileNumber() throws Throwable {
		WelcomeToeezyPage welcome=new WelcomeToeezyPage(driver);
		welcome.getLoginBtn(driver).click();
		SignUpForeezyPage signup=new SignUpForeezyPage(driver);
		signup.getEnterphoneNumbertxb().click();
		signup.getCountryDropDown().click();
		signup.getSearchCountrytxb(driver).sendKeys(file.getDataFromPropertFile("countryName"));
		signup.getCountrylist(driver).click();
		signup.getEnterphoneNumbertxb().sendKeys(file.getDataFromPropertFile("loginNumber"));
		signup.getNextbtn(driver).click();
		OTPPage otp=new OTPPage(driver);
		boolean text = otp.getDidntGetOtpTxt().isDisplayed();
		Assert.assertTrue(text);
	}

}
