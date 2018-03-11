package com.ac.login;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSuite{

	public WebDriver driver;
	public void launchWebsite()
	{
		System.setProperty("webdriver.gecko.driver","C:\\Users\\soumya.billava\\Downloads\\geckodriver\\geckodriver.exe");
		driver=new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://www.dtolegv:8888");
	}

	public void login(String Username, String Password)
	{
		driver.findElement(By.id("a")).sendKeys("Username");
		driver.findElement(By.id("b")).sendKeys("Password");
		driver.findElement(By.id("c")).click();	
	}
	

	//Test the Login Page with valid input data
	
	@Test(priority=0)	
	public void enterCorrectCredential()
	{
		launchWebsite();
		login("Soumya", "qwerty");
		String expected = "www.dtolegv:8888";
		Assert.assertEquals(driver.getCurrentUrl(), expected);
		driver.close();
	}

	//Test the Login Page with invalid user name
	
	@Test(priority=1)
	public void enterInvalidUsername()
	{
		launchWebsite();
		login("qwerty", "qwerty");
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}

	//Test the Login Page with numbers

	@Test(priority=2)
	public void enterNumber()
	{
		launchWebsite();
		login("1234", "qwerty");
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}

	//Test the Login Page with Special characters

	@Test(priority=3)
	public void enterSpecialCharacters()
	{
		launchWebsite();
		login("!@#$$", "qwerty");
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}


	//Test the Login Page with name and special characters

	@Test(priority=4)
	public void enterNameAndSpecialCharacter()
	{
		launchWebsite();
		login("Soumya", "!@#$%");
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}
	//Test the Login Page with Blank user name

	@Test(priority=5)
	public void enterBlankUsername()
	{
		launchWebsite();
		login(" ", "qwerty");
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}

	//Test the Login Page with Date

	@Test(priority=6)
	public void enterDate()
	{
		launchWebsite();
		login("12-12-2017", "qwerty");
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}
	//Test the Login Page with no password

	@Test(priority=7)
	public void enterNoPassword()
	{
		launchWebsite();
		driver.findElement(By.id("a")).sendKeys("Username");
		driver.findElement(By.id("c")).click();	
		String expected = "Logon failed for the user";
		String popup = driver.findElement(By.id("d")).getText();
		Assert.assertEquals(popup, expected);
		driver.close();
	}

	//Test Reset button after entering the “user name”

	@Test(priority=8)
	public void ResetUsername()
	{
		launchWebsite();
		driver.findElement(By.id("a")).sendKeys("Username");
		driver.findElement(By.id("e")).click();
		String emptyUsername = driver.findElement(By.id("a")).getText();
		Assert.assertTrue(emptyUsername.equals(""));
		driver.close();
	}
	
	//Test Reset button after entering the “password”

	@Test(priority=9)
	public void ResetPassword()
	{
		launchWebsite();
		driver.findElement(By.id("b")).sendKeys("Password");
		driver.findElement(By.id("e")).click();	
		String emptyPasswd = driver.findElement(By.id("b")).getText();
		Assert.assertTrue(emptyPasswd.equals(""));
		driver.close();
	}
	
	//Test Reset button after entering the “user name” and “Password”

	@Test(priority=10)
	public void ResetUsernamePassword()
	{
		launchWebsite();
		driver.findElement(By.id("a")).sendKeys("Username");
		driver.findElement(By.id("b")).sendKeys("Password");
		driver.findElement(By.id("e")).click();	
		String emptyUsername = driver.findElement(By.id("a")).getText();
		Assert.assertTrue(emptyUsername.equals(""));
		String emptyPasswd = driver.findElement(By.id("b")).getText();
		Assert.assertTrue(emptyPasswd.equals(""));
		driver.quit();
	}
	
	

}
