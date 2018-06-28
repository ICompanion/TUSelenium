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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

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
        element.click();
    }

    public void type(By locator, String inputText) {
        WebElement element = driver.findElement(locator);
        element.sendKeys(inputText);
    }

    public void refresh(){
        getDriver().navigate().refresh();
    }

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
