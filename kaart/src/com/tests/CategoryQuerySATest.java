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

public class CategoryQuerySATest extends SeleneseTestCase {
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
	public void testCategoryQuery() throws Exception {
		driver.get(baseUrl);
		driver.findElement(By.id("crosscountry")).click();

		driver.findElement(By.cssSelector("area")).click();
		try {
			assertEquals("punkt", driver.findElement(By.id("pointName"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nCouldn't find correct point.\n");
		}
		try {
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.contains("Points on map: 0"));
		} catch (Error e) {
			verificationErrors.append(e.toString() + "\nNo points on map!\n");
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
			System.out.println(verificationErrorString);
		} else {
			System.out.println("Category query tests were successful!");
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
