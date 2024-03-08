package com.entrata.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class loginEntrata {
	WebDriver driver;
	By signin = By.linkText("Sign In");
	By signinPropertyManagerLogin = By.xpath("//*[@class=\"button-default solid-dark-button\"]");
	By userName = By.id("entrata-username");
	By passWord = By.id("entrata-password");
	By login = By.linkText("Sign In");
	
	public loginEntrata(WebDriver driver) {
		this.driver = driver;
	}
	
	private void clickSignin() {
		driver.findElement(signin).click();
		System.out.println("Sign-in button Clicked!");
	}
	
	private void clickSigninPropertyManagerLogin() {
		driver.findElement(signinPropertyManagerLogin).click();	
		System.out.println("Property Mnager Login button Clicked");
	}

	private void setUserName(String username) {
		driver.findElement(userName).sendKeys(username);
		System.out.println("Username added to login form!");
	}

	private void setPassword(String password) {
		driver.findElement(passWord).sendKeys(password);
		System.out.println("Password added to login form!");
	}

	private void clickLogin() {
		driver.findElement(login).sendKeys(Keys.ENTER);
	}

	public void signinToEntrata(String username, String password) {
		this.clickSignin();
		this.clickSigninPropertyManagerLogin();
		this.setUserName(username);
		this.setPassword(password);
//		this.clickLogin();
	}
}
