package UiTestingPlayground.PageObjects;

import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClassAttributePage extends BasePage {
    public ClassAttributePage(WebDriver driver) {
        super(driver);
    }

    public ClassAttributePage clickButton(){
        getButton().click();
        return this;
    }

    public ClassAttributePage acceptAlert(){
        getAlert().accept();
        return this;
    }

    private WebElement getButton() {
        return driver.findElement(By.xpath("//button[contains(concat(' ', normalize-space(@class), ' '), ' btn-primary ')]"));
    }

    private Alert getAlert() {
        return driver.switchTo().alert();
    }
}
