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

public class RatingCommentSATest extends SeleneseTestCase {
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
	public void testRatingComment() throws Exception {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("proov.k@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("parool");
		driver.findElement(By.id("u_0_l")).click();
		driver.get(baseUrl);
		driver.findElement(By.id("login-windowid")).click();
		driver.findElement(By.cssSelector("img.fb_login_button")).click();
		driver.findElement(By.id("crosscountry")).click();
		driver.findElement(By.cssSelector("area")).click();

		driver.findElement(By.xpath("//div[@id='rating_bar']/ul/li[3]"))
				.click();

		try {
			assertEquals("Rating is posted", closeAlertAndGetItsText());

		} catch (Exception e) {
			verificationErrors.append(e.toString() + "\nAlert didn't show!\n");
		}

		
		
		driver.findElement(By.id("commentBox")).sendKeys("TestComment");
		driver.findElement(By.id("comment-buttonid")).click();
		try {
			assertEquals("Comment sent", closeAlertAndGetItsText());
		} catch (Exception e) {
			verificationErrors.append(e.toString() + "\nAlert didn't show!\n");
		}
		try {
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.matches("null"));
		} catch (Error e) {
			verificationErrors.append(e.toString() + "\nRating didn't change!\n");
		}

		try {
			assertEquals("TestComment",
					driver.findElement(By.xpath("(//li[@id='c1']/div/div/p)"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString() + "\nWrong comment text!\n");
		}
		try {
			assertEquals("Proov Kasutaja",
					driver.findElement(By.xpath("(//li[@id='c1']/div/p)"))
							.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString() + "\nWrong comment author!\n");
		}
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			System.out.println(verificationErrorString);
			fail(verificationErrorString);
		}else{
			System.out.println("Rating and comment tests were successful!");
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
