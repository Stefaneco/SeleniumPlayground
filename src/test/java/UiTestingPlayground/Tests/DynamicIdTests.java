package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.DynamicIdPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class DynamicIdTests extends BaseTest {

    DynamicIdPage page;
    @BeforeEach
    @Override
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/dynamicid");
        page = new DynamicIdPage(driver);
    }

    @Test
    public void dynamicId() {
        page.clickDynamicButton();
    }
}
