package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.ClassAttributePage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class ClassAttributeTests extends BaseTest {

    ClassAttributePage page;
    @Override
    @BeforeEach
    public void setUp(){
        super.setUp();
        driver.get("http://www.uitestingplayground.com/classattr");
        page = new ClassAttributePage(driver);
    }

    @Test
    public void classAttribute() {
        page.clickButton();
        page.acceptAlert();
    }
}
