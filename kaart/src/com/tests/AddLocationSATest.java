package com.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.SeleneseTestCase;

public class AddLocationSATest extends SeleneseTestCase {
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
	public void testAddLocationViaButton() throws Exception {
		driver.get("https://www.facebook.com/");
		driver.findElement(By.id("email")).sendKeys("proov.k@hotmail.com");
		driver.findElement(By.id("pass")).sendKeys("parool");
		driver.findElement(By.id("u_0_l")).click();
		driver.get(baseUrl);
		driver.findElement(By.id("login-windowid")).click();
	    driver.findElement(By.cssSelector("img.fb_login_button")).click();
		WebDriverWait wait = new WebDriverWait(driver, 5);

	    driver.findElement(By.id("marker")).click();
	    WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("area[title=\"Add new location\"]")));
		driver.findElement(By.cssSelector("area[title=\"Add new location\"]"))
				.click();
	    driver.findElement(By.name("remove-marker")).click();
	    driver.findElement(By.id("marker")).click();
	    WebElement element2 = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("area[title=\"Add new location\"]")));
		driver.findElement(By.cssSelector("area[title=\"Add new location\"]")).click();
	    driver.findElement(By.name("pName")).clear();
	    driver.findElement(By.name("pName")).sendKeys("Proov");
	    driver.findElement(By.name("pDesc")).clear();
	    driver.findElement(By.name("pDesc")).sendKeys("Proov");
	    driver.findElement(By.id("rowbox")).click();

	    driver.findElement(By.name("save-marker")).click();
	    
		assertEquals("Point saved", closeAlertAndGetItsText());

	    driver.findElement(By.id("rowing")).click();
	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("area")));

	    
	    try {
			assertEquals("false",driver.findElement(By.id("count")).getText()
					.matches("Points on map: 0"));
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nNo points loaded from database!\n");
		}
	    driver.findElement(By.cssSelector("area")).click();
	    driver.findElement(By.name("remove-marker")).click();
	    try {
			assertEquals("true",driver.findElement(By.id("count")).getText()
					.matches("Points on map: 0"));
		} catch (Error e) {
			verificationErrors.append(e.toString()
					+ "\nNo points loaded from database!\n");
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
			System.out.println("Location adding tests were successful!");
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
