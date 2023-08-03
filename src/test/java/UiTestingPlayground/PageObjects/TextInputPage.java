package UiTestingPlayground.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TextInputPage extends BasePage{
    public TextInputPage(WebDriver driver) {
        super(driver);
    }

    public TextInputPage clickButton() {
        getButton().click();
        return this;
    }

    public TextInputPage putTextToForm(String text) {
        getForm().sendKeys(text);
        return this;
    }

    public String getButtonText() {
        return getButton().getText();
    }
    private WebElement getForm() {
        return driver.findElement(By.id("newButtonName"));
    }

    private WebElement getButton() {
        return driver.findElement(By.id("updatingButton"));
    }
}
