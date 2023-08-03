package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.HiddenLayersPage;
import UiTestingPlayground.PageObjects.TextInputPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextInputTests extends BaseTest{

    TextInputPage page;
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/textinput");
        page = new TextInputPage(driver);
    }

    @Test
    public void textInput() {
        var testInputText = "This button is now called Joe";

        page.putTextToForm(testInputText);
        page.clickButton();
        assertEquals(testInputText, page.getButtonText());
    }
}
