package UiTestingPlayground.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DynamicIdPage extends BasePage {

    WebElement dynamicButton =  driver.findElement(By.xpath("//button[contains(text(), \"Button with Dynamic ID\")]"));
    public DynamicIdPage(WebDriver driver) {
        super(driver);
    }

    public DynamicIdPage clickDynamicButton() {
        dynamicButton.click();
        return this;
    }
}
