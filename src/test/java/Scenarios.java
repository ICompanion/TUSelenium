/*
 * ==========================================================================================
 * =                            JAHIA'S ENTERPRISE DISTRIBUTION                             =
 * ==========================================================================================
 *
 *                                  http://www.jahia.com
 *
 * JAHIA'S ENTERPRISE DISTRIBUTIONS LICENSING - IMPORTANT INFORMATION
 * ==========================================================================================
 *
 *     Copyright (C) 2002-2018 Jahia Solutions Group. All rights reserved.
 *
 *     This file is part of a Jahia's Enterprise Distribution.
 *
 *     Jahia's Enterprise Distributions must be used in accordance with the terms
 *     contained in the Jahia Solutions Group Terms &amp; Conditions as well as
 *     the Jahia Sustainable Enterprise License (JSEL).
 *
 *     For questions regarding licensing, support, production usage...
 *     please contact our team at sales@jahia.com or go to http://www.jahia.com/license.
 *
 * ==========================================================================================
 */

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

/**
 * Short description of the class
 *
 * @author tdubreucq
 */
public class Scenarios {

    static Functions functions;

    @BeforeAll
    public static void initialize() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        functions = new Functions(driver, "http://localhost:3000/", 5000);
        functions.init();
    }

    @Test
    public void loginTest() throws Exception {
        try {
            // Let the user actually see something!
            functions.type(By.id("login"), "IZ500");
            functions.type(By.id("pwd"), "wrongpwd");
            functions.click(By.id("loginsubmit"));
            functions.implicitWait(1500);
            functions.assertText(By.id("serveranswer"), "Authentication failed. User not found.");
            functions.type(By.id("login"), "IZ500");
            functions.type(By.id("pwd"), "dj500");
            functions.click(By.id("loginsubmit"));
            functions.implicitWait(1500);
            functions.assertText(By.id("welcome"), "Welcome M. Delaye");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @AfterAll
    public static void stop() {
        functions.quit();
    }

}
