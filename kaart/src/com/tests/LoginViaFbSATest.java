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

public class LoginViaFbSATest extends SeleneseTestCase {
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
	public void testLoginViaFb() throws Exception {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("proov.k@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("parool");
		driver.findElement(By.id("u_0_l")).click();
		driver.get(baseUrl);
		driver.findElement(By.id("login-windowid")).click();
		driver.findElement(By.cssSelector("img.btn_close")).click();
		driver.findElement(By.id("login-windowid2")).click();
		driver.findElement(By.cssSelector("img.btn_close")).click();
		driver.findElement(By.id("login-windowid")).click();
		driver.findElement(By.cssSelector("img.fb_login_button")).click();

		try {
			assertEquals("Log out", driver.findElement(By.id("login-windowid"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nLogin button text didn't change.\n");
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			System.out.println(verificationErrorString);
			fail(verificationErrorString);
		} else {
			System.out.println("Log in tests via Facebook were successful!");
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
