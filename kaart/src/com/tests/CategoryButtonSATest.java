package com.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import com.thoughtworks.selenium.SeleneseTestCase;

public class CategoryButtonSATest extends SeleneseTestCase {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://spordikaart.appspot.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testCategoryButton() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("football")).click();
		driver.findElement(By.id("basketball")).click();
		driver.findElement(By.id("volleyball")).click();
		driver.findElement(By.id("tennis")).click();
		driver.findElement(By.id("tabletennis")).click();
		driver.findElement(By.id("pool")).click();
		driver.findElement(By.id("bowling")).click();
		driver.findElement(By.id("golf")).click();
		driver.findElement(By.id("hockey")).click();
		driver.findElement(By.id("baseball")).click();
		driver.findElement(By.id("crosscountry")).click();
		driver.findElement(By.id("iceskating")).click();
		driver.findElement(By.id("snowboard")).click();
		driver.findElement(By.id("skatepark")).click();
		driver.findElement(By.id("archery")).click();
		driver.findElement(By.id("bicycle")).click();
		driver.findElement(By.id("camping")).click();
		driver.findElement(By.id("diving")).click();
		driver.findElement(By.id("rowing")).click();
		driver.findElement(By.id("skating")).click();
		driver.findElement(By.id("swimming")).click();
		driver.findElement(By.id("running")).click();
		driver.findElement(By.id("gym")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			System.out.println(verificationErrorString);
			fail(verificationErrorString);
		} else {
			System.out.println("Category button tests were successful!");
		}
	}

	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
