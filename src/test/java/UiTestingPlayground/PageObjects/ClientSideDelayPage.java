package UiTestingPlayground.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ClientSideDelayPage extends BasePage{
    public ClientSideDelayPage(WebDriver driver) {
        super(driver);
    }
    public ClientSideDelayPage clickAjaxButton() {
        getAjaxButton().click();
        return this;
    }

    public String getTextParagraphText() {
        return getTextParagraph().getText();
    }

    private WebElement getAjaxButton() {
        return driver.findElement(By.id("ajaxButton"));
    }

    private WebElement getTextParagraph() {
        return new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class=\"bg-success\"]"))
        );
    }
}
