package com.entrata.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.entrata.pages.loginEntrata;

public class TC_Entrata_Tests {

	WebDriver driver;
	loginEntrata objLogin;

	String url = "https://entrata.com";

	@BeforeTest
	public void setUp() {

		driver = new EdgeDriver();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
	}

	@Test(description = "Test Login feature of Entrata's website", priority = 1)
	void testLogin() {
		System.out.println("1st Test Started");
		driver.get(url);

		// I've created Page Object Model for 1st Test only
		objLogin = new loginEntrata(driver);
		objLogin.signinToEntrata("testUser", "1234");
		System.out.println("------------------------------------------------------------------------");
	}

	@Test(description = "Fill the form to schedule the Demo for Bill Pay", priority = 2)
	void scheduleDemoBillPay() throws InterruptedException {
		System.out.println("2nd Test Started");
		driver.get(url);

		// for this test will continue code statements from here
		// Hover to Products menu
		WebElement productMenu = driver.findElement(By.xpath("//div[contains(text(),'Products')]"));
		Actions actions = new Actions(driver);
		actions.moveToElement(productMenu).build().perform();
		System.out.println("Hover to Products Menu done successfully!");

		// Click Bill Pay option
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Products')]")));
		driver.findElement(By.partialLinkText("Bill Pay"));
		System.out.println("Bill Pay Link is clicked!");

		// click Schedule Demo button
		driver.findElement(By.xpath("//*[@class=\"button-default solid-dark-button\"]")).click();
		System.out.println("Schedule Demo button Clicked!");

		// Filling Schedule Demo form on Bill Pay Page
		driver.findElement(By.id("FirstName")).sendKeys("Sanket");
		driver.findElement(By.id("LastName")).sendKeys("Bhosale");
		driver.findElement(By.id("Email")).sendKeys("test@test.com");
		driver.findElement(By.id("Company")).sendKeys("Entrata");
		driver.findElement(By.id("Phone")).sendKeys("0123456789");
		
		WebElement unitCount = driver.findElement(By.id("Unit_Count__c"));
		Select sel = new Select(unitCount);
		sel.selectByIndex(1);
		
		driver.findElement(By.id("Title")).sendKeys("SDET");
		
		WebElement unitCount2 = driver.findElement(By.id("Unit_Count__c"));
		Select sel2 = new Select(unitCount2);
		sel2.selectByIndex(0);
		System.out.println("------------------------------------------------------------------------");
	}

	@Test(description = "Validate Solutions for property page title", priority = 3)
	void checkSolutionsOnPropertyPage() throws InterruptedException {
		System.out.println("3rd Test Started");
		driver.get(url);
		
		WebElement solutionsSection = driver.findElement(By.xpath("//div[@class='contained-banner-background']"));

		// Scroll to Solutions section in Home page
		Actions actions = new Actions(driver);
		actions.scrollToElement(solutionsSection).build().perform();
		System.out.println("Scrolled to Solutions Section");

		//Click Solutions page button
		driver.findElement(By.xpath("//a[contains(text(),'See All Solutions')]")).sendKeys(Keys.ENTER);
		System.out.println("\"See All Solutions\" Button Clicked");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains("Entrata Solutions Overview - Everything Entrata"));
		
		// Validate Title 		
		String title = "Entrata Solutions Overview - Everything Entrata";
		String extractedTitle = driver.getTitle();
		
		Assert.assertEquals(extractedTitle, title);	
		System.out.println("Title is validated! current page title " + extractedTitle + " is matching with " + title);
		System.out.println("------------------------------------------------------------------------");
	}

	@AfterTest
	public void quit() {
		driver.quit();
	}

}
