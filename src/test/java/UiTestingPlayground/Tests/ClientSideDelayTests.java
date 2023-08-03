package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.ClientSideDelayPage;
import UiTestingPlayground.PageObjects.HiddenLayersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ClientSideDelayTests extends BaseTest{

    ClientSideDelayPage page;
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/clientdelay");
        page = new ClientSideDelayPage(driver);
    }

    @Test
    public void clientSideDelay() {
        page.clickAjaxButton();
        var textGeneratedByClient = page.getTextParagraphText();
        assertEquals("Data calculated on the client side.", textGeneratedByClient);
    }
}