package UiTestingPlayground.Tests;

import UiTestingPlayground.PageObjects.ClickPage;
import UiTestingPlayground.PageObjects.HiddenLayersPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ClickTests extends BaseTest{

    ClickPage page;
    @Override
    @BeforeEach
    public void setUp() {
        super.setUp();
        driver.get("http://www.uitestingplayground.com/click");
        page = new ClickPage(driver);
    }

    @Test
    public void click() {
        page.clickBadButton();
        //move mouse away so the button is not hovered when getting the color
        //it was resulting in flaky tests otherwise as it sometimes was getting hovered value and sometimes not
        page.moveMouseToCorner();
        var buttonColorHex = page.getBadButtonColorAsHexString();
        assertEquals("#28a745", buttonColorHex);
    }
}