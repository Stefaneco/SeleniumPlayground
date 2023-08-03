import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class IteraQaNetTest {

    /*
    https://itera-qa.azurewebsites.net/home/practice
    Level: Beginner

    Notable selenium elements:
        - use of //parent in xpath
        - selecting elements form dropdown list
     */

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }

    @Test
    public void exercise1() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");
        var name = "Maurycy";
        var mobile = "109101102";
        var email = "abc@d.e";
        var password = "password";

        var nameElement = driver.findElement(By.id("name"));
        var phoneElement = driver.findElement(By.id("phone"));
        var emailElement = driver.findElement(By.id("email"));
        var passwordElement = driver.findElement(By.id("password"));
        var submitButton = driver.findElement(By.name("submit"));

        nameElement.sendKeys(name);
        phoneElement.sendKeys(mobile);
        emailElement.sendKeys(email);
        passwordElement.sendKeys(password);

        submitButton.click();

    }

    @Test
    public void exercise2() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");

        var genderElement = driver.findElement(By.id("male"));
        var tuesdayElement = driver.findElement(By.id("tuesday"));
        var fridayElement = driver.findElement(By.id("friday"));

        genderElement.click();
        tuesdayElement.click();
        fridayElement.click();
    }

    @Test
    public void exercise3() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");

        var travelElement = driver.findElement(By.xpath("//select[@class=\"custom-select\"]"));
        Select select = new Select(travelElement);
        select.selectByVisibleText("Norway");
    }

    @Test
    public void exercise4() {
        driver.get("https://itera-qa.azurewebsites.net/home/automation");

        var yearElement = driver.findElement(By.xpath("//input[@id=\"2years\"]//parent::div"));
        var seleniumElement = driver.findElement(By.xpath("//input[@id=\"selenium\"]//parent::div"));
        var testNGElement = driver.findElement(By.xpath("//input[@id=\"testng\"]//parent::div"));
        yearElement.click();
        seleniumElement.click();
        testNGElement.click();
    }
}
