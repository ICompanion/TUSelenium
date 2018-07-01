import org.apache.commons.exec.OS;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.MoveTargetOutOfBoundsException;
import org.openqa.selenium.internal.WrapsDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.junit.jupiter.api.Assertions;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Short description of the class
 *
 * @author tdubreucq
 */
public class Functions {

    private String baseUrl;
    private long implicitDriverTimeout;
    private WebDriver driver;

    public Functions(WebDriver driver, String baseUrl, long implicitDriverTimeout) {
        this.driver = driver;
        this.implicitDriverTimeout = implicitDriverTimeout;
        this.baseUrl = baseUrl;
    }

    public void init() {
        driver.get(baseUrl);
    }

    public void click(By locator) {
        WebElement element = driver.findElement(locator);
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click().perform();
    }

    public void type(By locator, String inputText) {
        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(inputText);
    }

    public void assertText(By locator, String textExpected) {
        WebElement element = driver.findElement(locator);
        Assertions.assertEquals(textExpected,element.getText());
    }

    public void assertContains(By locator, String textToContains) {
        WebElement element = driver.findElement(locator);
        Assertions.assertTrue(StringUtils.contains(element.getText(), textToContains));
    }

    public void implicitWait(int timeInMs) throws InterruptedException {
        Thread.sleep(timeInMs);
    };

    public void isDisplayed(By locator) {
        WebElement element = driver.findElement(locator);
        Assertions.assertTrue(element.isDisplayed());
    }

    public void refresh(){
        getDriver().navigate().refresh();
    }

    public void quit() { getDriver().quit(); }

    public String getBaseUrl() {
        return baseUrl;
    }

    public long getImplicitDriverTimeout() {
        return implicitDriverTimeout;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
