package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import base.PageBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends PageBase {
	public LoginPage(WebDriver driver) {
		super(driver);		
	}


	@FindBy(id="email")
	public static WebElement usernameField;
	@FindBy(id="pass") public static WebElement passwordField;
	@FindBy(id="loginbutton")
	public static WebElement loginButton;
	@FindBy(xpath = "//div[@class='bcvklqu9 nzypyw8j']")
	public static WebElement welcomeMessage;

	public void userLogin(String userName,String password)
	{
		System.out.println("Login to Facebook ...");
		usernameField.sendKeys(userName);
		passwordField.sendKeys(password);
		loginButton.click();
	}
	 public String getWelcomeMessage() {
	        return welcomeMessage.getText();
	    }

		/*
	 public String getHomeText() {
		 try {
			 return getText(HomeText);
		} catch (Exception e) {
		
			// TODO: handle exception
			return getText(HomeText);
		}
		 
		 
	    }*/
	 

	

}
