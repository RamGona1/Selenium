package selenium101;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.Parameters;

public class BrowserTest {

    public WebDriver driver;

    
    @BeforeClass
    @Parameters("browser")
    public void setUp(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
    }

    @Test
    public void testOpenGoogle() {
        driver.get("https://www.lambdatest.com/selenium-playground");
        WebElement simpleFormDemoLink = driver.findElement(By.linkText("Simple Form Demo"));
        simpleFormDemoLink.click();
        String currentURL = driver.getCurrentUrl();
        Assert.assertTrue(currentURL.contains("simple-form-demo"), "URL does not contain 'simple-form-demo'");
        String message = "Welcome to LambdaTest";
        WebElement messageTextbox = driver.findElement(By.xpath("//input[@id='user-message']"));
        messageTextbox.sendKeys(message);
        WebElement getCheckedValueButton = driver.findElement(By.xpath("//button[@id='showInput']"));
        getCheckedValueButton.click();
        WebElement messageDisplayed = driver.findElement(By.xpath("//div[@id='user-message']/p[@id='message']"));
        String displayedMessage = messageDisplayed.getText();
        Assert.assertEquals(displayedMessage, message, "The displayed message does not match the entered message");

    }

    @Test
    public void testSearchBox() {
    	 
        driver.get("https://www.lambdatest.com/selenium-playground");

        
        WebElement dragDropSlidersLink = driver.findElement(By.linkText("Drag & Drop Sliders"));
        dragDropSlidersLink.click();

        WebElement slider = driver.findElement(By.id("slider3"));
        WebElement rangeValue = driver.findElement(By.xpath("//div[@id='slider3']//div/input"));
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(slider, 95, 0).perform();
        String displayedValue = rangeValue.getText();
        Assert.assertEquals(displayedValue, "95", "The slider value did not update to 95");
    }
    
    @Test
    public void testInputSubmit() {
    	 
    	driver.get("https://www.lambdatest.com/selenium-playground");

        WebElement inputFormSubmitLink = driver.findElement(By.linkText("Input Form Submit"));
        inputFormSubmitLink.click();

        WebElement submitButton = driver.findElement(By.xpath("//form[@id='seleniumform']//button[@type='submit']"));
        submitButton.click();

        WebElement nameField = driver.findElement(By.name("name"));
        nameField.sendKeys("John Doe");
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("john.doe@example.com");
        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.sendKeys("123@123");
        WebElement companyField = driver.findElement(By.name("company"));
        companyField.sendKeys("persistent systems LTD");
        WebElement websiteField = driver.findElement(By.name("website"));
        websiteField.sendKeys("https://persistentsystems.com");
        WebElement countryDropdown = driver.findElement(By.name("country"));
        Select countrySelect = new Select(countryDropdown);
        countrySelect.selectByVisibleText("United States");
        WebElement cityField = driver.findElement(By.name("city"));
        cityField.sendKeys("Arizona");
        WebElement address1Field = driver.findElement(By.name("address_line1"));
        address1Field.sendKeys("231 Main");
        WebElement address2Field = driver.findElement(By.name("address_line2"));
        address2Field.sendKeys("Cross Roads 2");
        
        WebElement stateField = driver.findElement(By.xpath("//input[@id='inputState']"));
        stateField.sendKeys("Arizona");
        WebElement zipField = driver.findElement(By.name("zip"));
        zipField.sendKeys("85001");
        
        
        submitButton.click();

        WebElement successMessage = driver.findElement(By.cssSelector("//p[@class='success-msg hidden']"));
        String successText = successMessage.getText();
        Assert.assertTrue(successText.contains("Thanks for contacting us, we will get back to you shortly."), "Success message is not displayed correctly.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

