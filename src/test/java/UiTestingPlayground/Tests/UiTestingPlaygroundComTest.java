package UiTestingPlayground.Tests;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class UiTestingPlaygroundComTest {

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }

    @Test
    public void dynamicId() {
        driver.get("http://www.uitestingplayground.com/dynamicid");
        var button = driver.findElement(By.xpath("//button[contains(text(), \"Button with Dynamic ID\")]"));
        button.click();
    }

    @Test
    public void classAttribute() {
        driver.get("http://www.uitestingplayground.com/classattr");
        var button = driver.findElement(By.xpath("//button[contains(concat(' ', normalize-space(@class), ' '), ' btn-primary ')]"));
        button.click();
        var alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test
    public void hiddenLayers() {
        driver.get("http://www.uitestingplayground.com/hiddenlayers");
        var greenButton = driver.findElement(By.id("greenButton"));
        greenButton.click();
        //clicking hidden element throws ElementClickInterceptedException
        assertThrows(ElementClickInterceptedException.class, greenButton::click);
    }

    @Test
    public void loadDelay() {
        driver.get("http://www.uitestingplayground.com/");
        var menuButton = driver.findElement(By.xpath("//a[contains(text(), \"Load Delay\")]"));
        menuButton.click();
        var pageButton = new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn.btn-primary"))
        );
        pageButton.click();
    }

    @Test
    public void ajaxData() {
        driver.get("http://www.uitestingplayground.com/ajax");
        var button = driver.findElement(By.id("ajaxButton"));
        button.click();
        var textParagraph = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class=\"bg-success\"]"))
        );
        var textFromServer = textParagraph.getText();
        assertEquals("Data loaded with AJAX get request.", textFromServer);
    }

    @Test
    public void clientSideDelay() {
        driver.get("http://www.uitestingplayground.com/clientdelay");
        var button = driver.findElement(By.id("ajaxButton"));
        button.click();
        var textParagraph = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[@class=\"bg-success\"]"))
        );
        var textGeneratedByClient = textParagraph.getText();
        assertEquals("Data calculated on the client side.", textGeneratedByClient);
    }

    @Test
    public void click() {
        driver.get("http://www.uitestingplayground.com/click");
        var button = driver.findElement(By.id("badButton"));
        button.click();

        //move mouse away so the button is not hovered when getting the color
        //it was resulting in flaky tests otherwise as it sometimes was getting hovered value and sometimes not
        new Actions(driver).moveByOffset(0, 0).perform();

        var successButton = driver.findElement(By.id("badButton"));
        var buttonColor = successButton.getCssValue("background-color");
        var buttonColorHex = Color.fromString(buttonColor).asHex();
        assertEquals("#28a745", buttonColorHex);
    }

    @Test
    public void textInput() {
        driver.get("http://www.uitestingplayground.com/textinput");
        var testInputText = "This button is now called Joe";
        var form = driver.findElement(By.id("newButtonName"));
        var button = driver.findElement(By.id("updatingButton"));

        form.sendKeys(testInputText);

        button.click();
        assertEquals(testInputText, button.getText());
    }

    @Test
    public void scrollbars() {
        driver.get("http://www.uitestingplayground.com/scrollbars");
        var button = driver.findElement(By.id("hidingButton"));
        //.click() scrolls on its own but wanted to use this anyway as otherwise the task doesn't add value
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }

    @Test
    public void dynamicTable() {
        driver.get("http://www.uitestingplayground.com/dynamictable");
        var referenceElement = driver.findElement(By.cssSelector("p.bg-warning"));
        ///html/body/section/div/div/div[3]/div[4]/span[4]

        var tableElement = driver.findElement(By.xpath(
                "//span[@role=\"cell\" and contains(text(), \"Chrome\")]/following-sibling::span[contains(text(), '%')]")
        );

        assertEquals(referenceElement.getText(), "Chrome CPU: " + tableElement.getText());
    }

    @Test
    public void verifyText() {
        driver.get("http://www.uitestingplayground.com/verifytext");
        var helloElement = driver.findElement(By.xpath("//div[@class=\"bg-primary\"]//span[.=normalize-space(.)='Welcome UserName!']"));

    }

    @Test
    public void progressBar() {
        driver.get("http://www.uitestingplayground.com/progressbar");
        var startButton = driver.findElement(By.id("startButton"));
        var stopButton = driver.findElement(By.id("stopButton"));
        var progressBar = driver.findElement(By.id("progressBar"));

        startButton.click();
        new WebDriverWait(driver, Duration.ofSeconds(60)).until(
                ExpectedConditions.textToBePresentInElement(
                        progressBar,
                        "75%"
                )
        );
        stopButton.click();
    }

    @Test
    public void visibility() {
        driver.get("http://www.uitestingplayground.com/visibility");
        var hideButton = driver.findElement(By.id("hideButton"));
        hideButton.click();

        var removedButtons = driver.findElements(By.id("removedButton"));
        var zeroWidthButton = driver.findElement(By.id("zeroWidthButton"));
        var overlappedButton = driver.findElement(By.id("overlappedButton"));
        var transparentButton = driver.findElement(By.id("transparentButton"));
        var invisibleButton = driver.findElement(By.id("invisibleButton"));
        var notDisplayedButton = driver.findElement(By.id("notdisplayedButton"));
        var offscreenButton = driver.findElement(By.id("offscreenButton"));

        assertTrue(removedButtons.isEmpty());
        assertFalse(zeroWidthButton.isDisplayed());
        // isDisplayed returns true for overlapped elements
        assertThrows(ElementClickInterceptedException.class, overlappedButton::click);
        assertFalse(transparentButton.isDisplayed());
        assertFalse(invisibleButton.isDisplayed());
        assertFalse(notDisplayedButton.isDisplayed());
        assertFalse(offscreenButton.isDisplayed());
    }

    @Test
    public void sampleApp() {
        driver.get("http://www.uitestingplayground.com/sampleapp");
        var username = "HelloWorld";
        var password = "pwd";
        var passwordElement = driver.findElement(By.xpath("//input[@type=\"password\"]"));
        var usernameElement = driver.findElement(By.xpath("//input[@type=\"text\"]"));
        var loginButton = driver.findElement(By.id("login"));

        passwordElement.sendKeys(password);
        usernameElement.sendKeys(username);

        loginButton.click();

        var welcomeElement = driver.findElement(By.id("loginstatus"));

        assertEquals(String.format("Welcome, %s!", username), welcomeElement.getText());
    }

    @Test
    public void mouseover() {
        driver.get("http://www.uitestingplayground.com/mouseover");

        var clickElement = driver.findElement(By.xpath("//a[@title=\"Click me\"]"));
        var actions = new Actions(driver);
        actions.doubleClick(clickElement).perform();

        var counterElement = driver.findElement(By.id("clickCount"));
        assertEquals("2", counterElement.getText());
    }

    @Test
    public void nbsp() {
        driver.get("http://www.uitestingplayground.com/nbsp");

        var button = driver.findElement(By.xpath("//button[text()='My\u00a0Button']"));
        button.click();
    }

    @Test
    public void overlapped() {
        driver.get("http://www.uitestingplayground.com/overlapped");
        var testName = "HelloWorld";
        var nameFieldElement = driver.findElement(By.id("name"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", nameFieldElement);
        nameFieldElement.sendKeys(testName);
        assertEquals(testName, nameFieldElement.getAttribute("value"));
    }

    @Test
    public void shadowDom() {
        driver.get("http://www.uitestingplayground.com/shadowdom");
        var shadowDom = driver.findElement(By.cssSelector("body > section > div > guid-generator"));
        var root = shadowDom.getShadowRoot();

        var generateButton = root.findElement(By.id("buttonGenerate"));
        var copyButton = root.findElement(By.id("buttonCopy"));
        generateButton.click();
        copyButton.click();

        var textField = root.findElement(By.id("editField"));

        /*
        Even when testing manually the guid is not copied to clipboard by clicking the copyButton.
        I'm commenting out this part

        String clipboardText = "";
        try {
            clipboardText = (String)Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor);
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(textField.getAttribute("value"), clipboardText);
        */
    }

}
