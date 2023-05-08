import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

public class TheInternetTest {

    /*
    https://the-internet.herokuapp.com/
    Level: TBD

    Notable selenium elements:
        TBD

    The Internet provides a playground without specified exercises
    I've added my own tasks to each sub-site in the comments
     */

    WebDriver driver;

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void exit() {
        driver.quit();
    }

    /*
    1. Click add element 4 times
    2. Delete 3rd Added element
    3. assert that there are 3 delete buttons left
     */
    @Test
    public void add_remove_elements() {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
        var addElement = driver.findElement(By.xpath("//button[@onclick=\"addElement()\"]"));
        for(int i = 0; i<4; i++)
            addElement.click();
        var thirdDeleteButton = driver.findElement(By.xpath("//div[@id=\"elements\"]//button[3]"));
        thirdDeleteButton.click();
        var deleteElements = driver.findElements(By.xpath("//div[@id=\"elements\"]//button"));
        assertEquals(3, deleteElements.size());
    }

    /*
        1. Open basic_auth sub-site, use "admin" as username and password
        2. Assert that the user is signed in
     */
    @Test
    public void basic_auth(){
        var username = "admin";
        var password = "admin";
        var expectedText = "Congratulations! You must have the proper credentials.";
        driver.get("https://%s:%s@the-internet.herokuapp.com/basic_auth".formatted(username, password));
        var textElement = driver.findElement(By.xpath("//div[@class=\"example\"]/p"));
        assertEquals(expectedText, textElement.getText());
    }

    /*
        1. find all images in "example" container
        2. you can count broken images and working ones
        3. assert that there are 2 broken images and 1 working one
     */
    @Test
    public void broken_images(){
        var nofBrokenImages = 0;
        var nofWorkingImages = 0;

        driver.get("https://the-internet.herokuapp.com/broken_images");
        var images = driver.findElements(By.xpath("//div[@class=\"example\"]//img"));
        for(WebElement image : images){
            var width = image.getAttribute("naturalWidth");
            if(Objects.equals(width, "0")) nofBrokenImages += 1;
            else nofWorkingImages += 1;
        }
        assertEquals(2, nofBrokenImages);
        assertEquals(1, nofWorkingImages);
    }

    /*
     1. Find blue button
     2. Find red button
     3. Find green button
     4. Find edit button for a row with elements ending with "7". Assume that the list is not sorted.
     5. Find delete button for row 5.
     6. Get text from canvas and assert that it starts with "Answer"
     */

    @Test
    public void challenging_dom(){
        driver.get("https://the-internet.herokuapp.com/challenging_dom");

        var blueButton = driver.findElement(By.xpath("//a[@class=\"button\"]"));
        var redButton = driver.findElement(By.xpath("//a[@class=\"button alert\"]"));
        var greenButton = driver.findElement(By.xpath("//a[@class=\"button success\"]"));
        var editSeven = driver.findElement(By.xpath("//td[contains(text(), \"7\")]//following-sibling::td//child::a[@href=\"#edit\"]"));
        var deleteFifth = driver.findElement(By.xpath("//tr[5]//td//child::a[@href=\"#delete\"]"));

        /*
        I've trouble running tesseract on m1 mac. I will give it a go once I'm back on my x86 machine
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("/opt/homebrew/Cellar/tesseract/5.3.1/share/tessdata/");
        var canvas = driver.findElement(By.id("canvas"));
        var script = "return arguments[0].toDataURL('image/png').substring(21);";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String imageString = (String) js.executeScript(script, canvas);
        System.out.println(imageString);
        String base64Image = imageString.substring(imageString.indexOf(",") + 1);
        byte[] decodedImage = Base64.getDecoder().decode(base64Image);
        String result;
        try {
            result = tesseract.doOCR(ImageIO.read(new ByteArrayInputStream(decodedImage)));
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /*
     1. Select checkbox 1.
     2. Deselect checkbox 2.
     This one I'm a little unsure about. I would like to locate boxes by text, but I'm not sure how to do that.
     */
    @Test
    public void checkboxes() {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        var box1 = driver.findElement(By.xpath("//input[1]"));
        var box2 = driver.findElement(By.xpath("//input[2]"));
        box1.click();
        box2.click();
    }

    /*
       1. Right-click on the outlined box
       2. Click on the ok button in the alert box
     */
    @Test
    public void context_menu() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        var box = driver.findElement(By.id("hot-spot"));
        Actions actions = new Actions(driver);
        actions.contextClick(box).perform();
        driver.switchTo().alert().accept();
    }

    /*
      1. Open the-internet.herokuapp.com/digest_auth as an authenticated user (username: admin, password: admin)
      2. Assert that you have been authenticated
     */
    @Test
    public void digest_auth() {
        driver.get("https://admin:admin@the-internet.herokuapp.com/digest_auth");
        var authParagraph = driver.findElement(By.xpath("//div//p"));
        assertEquals("Congratulations! You must have the proper credentials.", authParagraph.getText());
    }

    /*
        1. refresh the-internet.herokuapp.com/disappearing_elements 6 times and
        count how many times the Gallery button appeared
     */
    @Test
    public void disappearing_elements() {
        var galleryCount = 0;
        driver.get("https://the-internet.herokuapp.com/disappearing_elements");
        for(int i = 0; i<6; i++){
            driver.navigate().refresh();
            var galleryButton = driver.findElements(By.xpath("//a[@href=\"/gallery/\"]"));
            galleryCount += galleryButton.size();
        }
    }

    /*
        1. assert that element with text "A" is the column on the left, and "B" is on the right.
        2. drag and drop element "A" to the right.
        3. assert that the elements have been swapped.

        NOTE issue with drag and drop https://github.com/w3c/webdriver/issues/1488
        Some way around it that worked for me:
        https://stackoverflow.com/questions/62571462/selenium-drag-and-drop-not-working-with-action-methods-is-there-any-alterative
     */
    @Test
    public void drag_and_drop() {
        driver.get("https://the-internet.herokuapp.com/drag_and_drop");
        var a = driver.findElement(By.xpath("//div[@id=\"column-a\"]//header"));
        var b = driver.findElement(By.xpath("//div[@id=\"column-b\"]//header"));

        assertEquals("A", a.getText());
        assertEquals("B", b.getText());

        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("""
                function createEvent(typeOfEvent) {
                var event =document.createEvent("CustomEvent");
                event.initCustomEvent(typeOfEvent,true, true, null);
                event.dataTransfer = {
                data: {},
                setData: function (key, value) {
                this.data[key] = value;
                },
                getData: function (key) {
                return this.data[key];
                }
                };
                return event;
                }

                function dispatchEvent(element, event,transferData) {
                if (transferData !== undefined) {
                event.dataTransfer = transferData;
                }
                if (element.dispatchEvent) {
                element.dispatchEvent(event);
                } else if (element.fireEvent) {
                element.fireEvent("on" + event.type, event);
                }
                }

                function simulateHTML5DragAndDrop(element, destination) {
                var dragStartEvent =createEvent('dragstart');
                dispatchEvent(element, dragStartEvent);
                var dropEvent = createEvent('drop');
                dispatchEvent(destination, dropEvent,dragStartEvent.dataTransfer);
                var dragEndEvent = createEvent('dragend');
                dispatchEvent(element, dragEndEvent,dropEvent.dataTransfer);
                }

                var source = arguments[0];
                var destination = arguments[1];
                simulateHTML5DragAndDrop(source,destination);""", a, b);

        a = driver.findElement(By.xpath("//div[@id=\"column-a\"]//header"));
        b = driver.findElement(By.xpath("//div[@id=\"column-b\"]//header"));

        assertEquals("B", a.getText());
        assertEquals("A", b.getText());
    }

    /*
        1. select option 2 from the dropdown menu
        2. select option 1 from the dropdown menu
     */
    @Test
    public void dropdown() {
        driver.get("https://the-internet.herokuapp.com/dropdown");
        var dropdown = driver.findElement(By.xpath("//*[@id=\"dropdown\"]"));
        Select select = new Select(dropdown);
        select.selectByVisibleText("Option 2");
        select.selectByVisibleText("Option 1");
    }

    /*
        1. get text and image source from the second row
     */
    @Test
    public void dynamic_content() {
        driver.get("https://the-internet.herokuapp.com/dynamic_content");
        var text = driver
            .findElement(By.xpath("//div[@id=\"content\"]//div[@id=\"content\"]//div[@class=\"row\"][2]//div[2]"))
            .getText();
        var imageSrc = driver
            .findElement(By.xpath("//div[@id=\"content\"]//div[@id=\"content\"]//div[@class=\"row\"][2]//img"))
            .getAttribute("src");
    }

    /*
        1. Select checkbox
        2. Click on Remove button
        3. Confirm that the message "It's gone!" is displayed
        4. Confirm that the checkbox has been removed
        5. Click on Add button
        6. Confirm that the message "It's back!" is displayed
        6. Select checkbox
     */
    @Test
    public void dynamic_controls() {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        var swapButton = driver.findElement(By.xpath("//button[@onclick=\"swapCheckbox()\"]"));
        var checkbox = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
        checkbox.click();
        swapButton.click();
        var goneMessage = new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"It's gone!\"]"))
        );
        var checkboxList = driver.findElements(By.xpath("//input[@type=\"checkbox\"]"));
        assertEquals(0, checkboxList.size());
        swapButton.click();
        var backMessage = new WebDriverWait(driver, Duration.ofSeconds(30)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[text()=\"It's back!\"]"))
        );
        checkbox = driver.findElement(By.xpath("//input[@type=\"checkbox\"]"));
        checkbox.click();
    }

    /*
        1. assert that Hello World text is not visible to the user
        2. click start button
        3. assert that Hello World text is visible to the user
     */
    @Test
    public void dynamic_loading_1(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        var helloWorldElementList = driver.findElements(By.xpath("//div[@id=\"finish\"]//h4"));
        if(!helloWorldElementList.isEmpty()){
            assertFalse(helloWorldElementList.get(0).isDisplayed());
        }
        var startButton = driver.findElement(By.xpath("//div[@id=\"start\"]//button"));
        startButton.click();
        var helloWorldElement = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"finish\"]//h4"))
        );
        assertTrue(helloWorldElement.isDisplayed());
    }

    /*
        1. assert that Hello World text is not visible to the user
        2. click start button
        3. assert that Hello World text is visible to the user

        I think ideally your test case should look just like dynamic_loading_1
     */
    @Test
    public void dynamic_loading_2(){
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/2");
        var helloWorldElementList = driver.findElements(By.xpath("//div[@id=\"finish\"]//h4"));
        if(!helloWorldElementList.isEmpty()){
            assertFalse(helloWorldElementList.get(0).isDisplayed());
        }
        var startButton = driver.findElement(By.xpath("//div[@id=\"start\"]//button"));
        startButton.click();
        var helloWorldElement = new WebDriverWait(driver, Duration.ofSeconds(20)).until(
                ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id=\"finish\"]//h4"))
        );
        assertTrue(helloWorldElement.isDisplayed());
    }
}
