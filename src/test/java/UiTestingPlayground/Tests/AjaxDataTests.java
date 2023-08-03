package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.AjaxDataPage;
import UiTestingPlayground.PageObjects.HiddenLayersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AjaxDataTests extends BaseTest{

    AjaxDataPage page;
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/ajax");
        page = new AjaxDataPage(driver);
    }

    @Test
    public void ajaxData() {
        page.clickAjaxButton();
        var textFromServer = page.getTextParagraphText();
        assertEquals("Data loaded with AJAX get request.", textFromServer);
    }
}
