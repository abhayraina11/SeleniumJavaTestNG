package rahulshettacademy.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import rahulshettyacademy.pageobjects.LoginPage;

public class BaseTest {
	
	protected WebDriver driver;
	//is declared at the class level in BaseTest to ensure that it can be accessed throughout the class and by subclasses	
	protected LoginPage loginPage;
	
	public WebDriver initializeDriver() throws IOException
	{
		//maintain global properties file where you can store on which browser you want to execute. Based on the global prop browser
		//will get invoked. To setup global properties in java we have properties class. This class will read the properties and
		//decide on runtime on which browser your framework has to be executed.
		Properties prop = new Properties();
		
		//To send the file in the form of an input stream we need to use a java class called FileInputStream 
		//which can connect your file into input stream object. 
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\rahulshettyacademy\\resources\\GlobalData.properties");
	
		// here we need to load the GlobalData properties file. This method will parse the file and extract all the 
		//key value pairs from it. It is expecting argument in the form of input stream.
		prop.load(fis);
		
		String browserName = prop.getProperty("browser");
		
		if(browserName.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","C:\\Users\\HP\\Documents\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public Map<String, List<HashMap<String, String>>> getJsonDataToMap(String filePath) throws IOException
	{
		//read json and convert it to string
		String jsonContent = FileUtils.readFileToString(new File(filePath),StandardCharsets.UTF_8);
		//Need to use jackson databind utility to convert jsonContent string to HashMap
		
		ObjectMapper mapper = new ObjectMapper(); 
		Map<String, List<HashMap<String, String>>> data = mapper.readValue(jsonContent, new TypeReference<Map<String, List<HashMap<String, String>>>>() {});
		//The data is now retreived in the form of a List
		return data;
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public LoginPage launchApplication() throws IOException
	{
		driver = initializeDriver();
		loginPage = new LoginPage(driver);
		loginPage.goTo();
		return loginPage;
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.close();
	}
	
}
