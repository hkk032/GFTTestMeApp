package stepDefinition;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.testng.asserts.SoftAssert;

public class TestMeLoginDefinition {
	WebDriver driver;
	SoftAssert sa = new SoftAssert();
	@Given("^navigate to home page$")
	public void navigate_to_home_page() throws Throwable {
		System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	}
	
	@When("^user enters \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_enters_and(String uname, String pass) throws Throwable {
		driver.findElement(By.linkText("SignIn")).click();

		String loginTitle = driver.getTitle();
		sa.assertEquals("Login", loginTitle);			
		driver.findElement(By.id("userName")).sendKeys(uname);
		driver.findElement(By.id("password")).sendKeys(pass);		
		driver.findElement(By.name("Login")).click();
		System.out.println("Username: "+uname);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
	}
	
	@Then("^Message login successfully$")
	public void message_login_successfully() throws Throwable {
		String nxtPage = driver.getTitle();
		
		if (!(nxtPage.equals("Login"))) {
			System.out.println("Signed IN successfully");
			driver.findElement(By.linkText("SignOut")).click();
			System.out.println("Signed OUT successfully");
		}
		else {		
			String errmsg = driver.findElement(By.xpath("(//div[@id='errormsg'])[2]")).getText();
			System.out.println(errmsg);
			sa.assertEquals("Username or Password is wrong here!!!", errmsg);
		}
		
		sa.assertAll();
		driver.quit();
	}
}
