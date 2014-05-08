package com.tests;

import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MixedTests {
	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://spordikaart.appspot.com/";
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("proov.k@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("parool");
		driver.findElement(By.id("u_0_l")).click();
		driver.get(baseUrl);
		driver.findElement(By.id("login-windowid")).click();
		driver.findElement(By.cssSelector("img.btn_close")).click();
		// driver.findElement(By.id("login-windowid2")).click();
		// driver.findElement(By.cssSelector("img.btn_close")).click();
		driver.findElement(By.id("login-windowid")).click();
		driver.findElement(By.cssSelector("img.fb_login_button")).click();

		WebDriverWait wait = new WebDriverWait(driver, 5);

		try {
			assertEquals("Log out", driver.findElement(By.id("login-windowid"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nLogin button text didn't change!\n");
		}

		driver.findElement(By.id("marker")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("area[title=\"Add new location\"]")));
		driver.findElement(By.cssSelector("area[title=\"Add new location\"]"))
				.click();
		driver.findElement(By.name("remove-marker")).click();
		driver.findElement(By.id("marker")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("area[title=\"Add new location\"]")));
		driver.findElement(By.cssSelector("area[title=\"Add new location\"]"))
				.click();
		driver.findElement(By.name("pName")).clear();
		driver.findElement(By.name("pName")).sendKeys("Proov");
		driver.findElement(By.name("pDesc")).clear();
		driver.findElement(By.name("pDesc")).sendKeys("Proov");
		driver.findElement(By.id("rowbox")).click();

		driver.findElement(By.name("save-marker")).click();

		assertEquals("Point saved", closeAlertAndGetItsText());

		driver.findElement(By.id("crosscountry")).click();
		try {
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.contains("Points on map: 0"));
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nPoints count not updated!\n");
		}

		try {
			driver.findElement(By.cssSelector("area")).click();
			assertEquals("punkt", driver.findElement(By.id("pointName"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nCouldn't find correct point.\n");
		}

		driver.findElement(By.id("rowing")).click();

		try {
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.contains("Points on map: 0"));
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nPoints count not updated!\n");
		}

		try {
			driver.findElement(By.cssSelector("area")).click();

		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nNo points loaded from database!\n");
		}

		try {
			assertEquals("Proov", driver.findElement(By.id("pointDescription"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nWrong point name(added point)!\n");
		}
		driver.findElement(By.xpath("//div[@id='rating_bar']/ul/li[3]"))
				.click();

		assertEquals("Rating is posted", closeAlertAndGetItsText());
		driver.findElement(
				By.xpath("//img[contains(@src,'https://maps.gstatic.com/mapfiles/api-3/images/mapcnt3.png')]"))
				.click();

		driver.findElement(By.cssSelector("area")).click();

		try {
			assertFalse(driver.findElement(By.cssSelector("BODY")).getText()
					.contains("null"));
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nRating didn't change!\n");
		}
		driver.findElement(By.id("commentBox")).sendKeys("TestComment");
		driver.findElement(By.id("comment-buttonid")).click();

		assertEquals("Comment sent", closeAlertAndGetItsText());

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
			verificationErrors.append(e.toString()
					+ "\nWrong comment author!\n");
		}
		driver.findElement(By.id("login-windowid")).click();
		try {
			assertEquals("Log in", driver.findElement(By.id("login-windowid"))
					.getText());
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nLogout button text didn't change!\n");
		}

		driver.findElement(By.id("rowing")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By
				.cssSelector("area")));
		driver.findElement(By.cssSelector("area")).click();
		driver.findElement(By.name("remove-marker")).click();
		driver.findElement(By.id("football")).click();
		driver.findElement(By.id("rowing")).click();

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			System.out.println(verificationErrorString);
			fail(verificationErrorString);
		} else {
			System.out.println("All tests worked correctly!");
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
