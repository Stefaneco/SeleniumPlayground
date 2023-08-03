package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.HiddenLayersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class HiddenLayersTests extends BaseTest{

    HiddenLayersPage page;
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/hiddenlayers");
        page = new HiddenLayersPage(driver);
    }

    @Test
    public void hiddenLayers() {
        page.clickGreenButton();
        //clicking hidden element throws ElementClickInterceptedException
        assertThrows(ElementClickInterceptedException.class, page::clickGreenButton);
    }
}
