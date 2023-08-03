package UiTestingPlayground.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadDelayPage extends BasePage{
    public LoadDelayPage(WebDriver driver) {
        super(driver);
    }

    public LoadDelayPage clickMenuButton() {
        getMenuButton().click();
        return this;
    }

    public LoadDelayPage clickPageButton() {
        getPageButton().click();
        return this;
    }

    private WebElement getMenuButton() {
        return driver.findElement(By.xpath("//a[contains(text(), \"Load Delay\")]"));
    }

    private WebElement getPageButton() {
        return new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))
        );
    }
}
