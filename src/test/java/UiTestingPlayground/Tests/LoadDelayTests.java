package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.HiddenLayersPage;
import UiTestingPlayground.PageObjects.LoadDelayPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoadDelayTests extends BaseTest{

    LoadDelayPage page;
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/");
        page = new LoadDelayPage(driver);
    }

    @Test
    public void loadDelay() {
        page.clickMenuButton();
        page.clickPageButton();
    }
}
