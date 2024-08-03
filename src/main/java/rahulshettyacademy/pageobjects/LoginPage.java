package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LoginPage extends AbstractComponent{
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory is used to reduce the syntax	
	
	@FindBy(xpath = "//input[@placeholder='Username']")
	WebElement usernameField;
	
	@FindBy(xpath = "//input[@placeholder='Password']")
	WebElement password;
	
	@FindBy(xpath = "//button[normalize-space()='Login']")
	WebElement login;
	
	//WebElement invalidLoginMsg = driver.findElement(By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text"));
	@FindBy(css="p.oxd-text.oxd-text--p.oxd-alert-content-text")
	WebElement invalidLoginMsg;
	
	//These are action methods for the above initialized webelements
	public void SuccessfulLogin(String username, String passw)
	{
		usernameField.sendKeys(username);
		password.sendKeys(passw);
		login.click();
	}
	
	public void goTo() 
	{
		driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
	}
	
	public WebElement InvalidLoginMsg()
	{
		return invalidLoginMsg;
	}
	
}
