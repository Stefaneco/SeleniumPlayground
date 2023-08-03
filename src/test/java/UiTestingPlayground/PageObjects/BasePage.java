package UiTestingPlayground.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class BasePage {
    WebDriver driver;

    public BasePage(WebDriver driver){
        this.driver = driver;
    }

    public BasePage moveMouseToCorner() {
        new Actions(driver).moveByOffset(0, 0).perform();
        return this;
    }
}
