package rahulshettyacademy;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettacademy.TestComponents.BaseTest;
import rahulshettyacademy.AbstractComponents.AbstractComponent;
import rahulshettyacademy.pageobjects.LoginPage;

public class OrangeHRMLogin extends BaseTest{

	@Test(dataProvider="getValidData", priority=1, groups = {"Smoke"})
	public void validLogin(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
//		LoginPage loginPage = launchApplication();
		loginPage.SuccessfulLogin(input.get("userID"), input.get("pass"));
		AbstractComponent abst = new AbstractComponent(driver);
		abst.URLContain("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
		String currentURL = driver.getCurrentUrl();
		System.out.println(currentURL);

	}
	
	@Test(dataProvider="getInvalidData", priority=2)
	public void InvalidLoginCreds(HashMap<String, String> input) throws IOException
	{
		//LoginPage loginPage = launchApplication();	
		loginPage.SuccessfulLogin(input.get("userID"), input.get("pass"));
		AbstractComponent abstractComponent = new AbstractComponent(driver);
		abstractComponent.waitForElementToAppear(By.cssSelector("p.oxd-text.oxd-text--p.oxd-alert-content-text"));
		WebElement invalidLoginMsg = loginPage.InvalidLoginMsg();
		//Hard Assertion: Validate if the error message is displayed
		Assert.assertTrue(invalidLoginMsg.isDisplayed(), "Error message is not displayed");
		// Hard Assertion: Validate the error message text
		String expectedErrorMessage = "Invalid credentials";
		Assert.assertEquals(invalidLoginMsg.getText(), expectedErrorMessage, "Error message text is incorrect");
	}
	
	
	@DataProvider
	public Object[][] getValidData() throws IOException {
		//getJsonDataToMap is a utility method that reads the JSON file from the specified path and converts it into a Map<String, List<HashMap<String, String>>>
		//The key in the map is a String (e.g., "validLoginData" or "invalidLoginData"), and the value is a List<HashMap<String, String>>
	    Map<String, List<HashMap<String, String>>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\LoginFunctionality.json");
	    
	    //Extracts the list of valid login data from the map using the key "validLoginData"
	    List<HashMap<String, String>> validLoginData = data.get("validLoginData");
	    
	    //Creates a 2D Object array where each row represents a single set of test data
	    //validLoginData.size() gives the number of test data sets
	    //Each HashMap (representing a set of credentials) is placed into the array
	    Object[][] result = new Object[validLoginData.size()][1];
	    for (int i = 0; i < validLoginData.size(); i++) {
	        result[i][0] = validLoginData.get(i);
	    }
	    //Returns the Object[][] array to TestNG, which will use it to supply data to the validLogin test method
	    return result;
	}

	@DataProvider
	public Object[][] getInvalidData() throws IOException {
	    Map<String, List<HashMap<String, String>>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\LoginFunctionality.json");
	    List<HashMap<String, String>> invalidLoginData = data.get("invalidLoginData");
	    
	    Object[][] result = new Object[invalidLoginData.size()][1];
	    for (int i = 0; i < invalidLoginData.size(); i++) {
	        result[i][0] = invalidLoginData.get(i);
	    }
	    return result;
	}
	
	public String getScreenshot(String testCaseName) throws IOException
	{
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}
	
//HashMap<String,String> map = new HashMap<String,String>();
//map.put("userID", "Admin");
//map.put("pass", "admin123");
//
//HashMap<String,String> map1 = new HashMap<String,String>();
//map1.put("userID", "Admin");
//map1.put("pass", "admin123");