package ranvijaybhaskar.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import ranvijaybhaskar.pageobjects.LandingPage;

public class BaseTest {
	
	String browser;
	public WebDriver driver;
	protected LandingPage landingPage;
	
	public WebDriver initalizeDriver() throws IOException {
		//properties class
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\ranvijaybhaskar\\resources\\GlobalData.properties");
		prop.load(fis);
		
		browser = System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
		
		if(browser.contains("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if(browser.contains("headless")) {
				options.addArguments("headless");
			}					
			driver = new ChromeDriver(options);
			//Remove Flakyness for Headless Mode
			driver.manage().window().setSize(new Dimension(1440, 900));
		}else if(browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
				
		}else if(browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();			
		}else {
			Assert.fail("The Browser Setup is not Configured for "+browser+". It might be due to spelling mistake or a missing browser");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException {
		driver = initalizeDriver();
		landingPage = new LandingPage(driver);
		landingPage.goTo();
		return landingPage;
	}
	

		public List<HashMap<String, String>> getJasonDataToMap(String path) throws IOException {
			//Read Json to String
			String jsonContent = FileUtils.readFileToString(new File(path), StandardCharsets.UTF_8);
			
			//String to HashMap -> Jackson Databind
			ObjectMapper mapper = new ObjectMapper();
			List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
			
			//{map, map2}
			
			return data;
		}


	
	@AfterMethod(alwaysRun=true)
	public void closeBrowser() throws InterruptedException {
		driver.quit();
		//Thread.sleep(2000);
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot)driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"//reports//"+testcaseName+".png");
		FileUtils.copyFile(source, dest);
		return System.getProperty("user.dir")+"//reports//"+testcaseName+".png";
	}
	

}
