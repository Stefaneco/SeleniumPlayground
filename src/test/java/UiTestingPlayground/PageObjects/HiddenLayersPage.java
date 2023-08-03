package UiTestingPlayground.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HiddenLayersPage extends BasePage {
    public HiddenLayersPage(WebDriver driver) {
        super(driver);
    }
    public HiddenLayersPage clickGreenButton() {
        getGreenButton().click();
        return this;
    }
    private WebElement getGreenButton() {
        return driver.findElement(By.id("greenButton"));
    }
}
