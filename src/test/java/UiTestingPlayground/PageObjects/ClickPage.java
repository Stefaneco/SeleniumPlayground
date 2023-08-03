package UiTestingPlayground.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

public class ClickPage extends BasePage {

    public ClickPage(WebDriver driver) {
        super(driver);
    }

    public ClickPage clickBadButton() {
        getBadButton().click();
        return this;
    }

    public String getBadButtonColorAsHexString() {
        var color = getBadButton().getCssValue("background-color");
        return Color.fromString(color).asHex();
    }

    private WebElement getBadButton() {
        return driver.findElement(By.id("badButton"));
    }


}
