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
import org.junit.jupiter.api.AfterEach;
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

    public static void initialize() {
        System.setProperty("webdriver.chrome.driver", "./chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        functions = new Functions(driver, "http://localhost:3000/", 5000);
        functions.init();
    }

    @BeforeAll
    public static void loginTest() throws Exception {
        try {
            initialize();
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
            functions.quit();
        }
    }

    @Test
    public void analysisTest() throws Exception {
        try {
            functions.refresh();
            functions.click(By.id("analysis"));
            functions.implicitWait(2500);
            functions.assertText(By.id("listtype"), "Liste de vos résultats :");
            functions.click(By.id("SG-MO-0001"));
            functions.implicitWait(1500);
            functions.assertText(By.id("details"),"Date : 2018-02-19\nRealized by Dr. : ANDRÉ");
            functions.click(By.name("okresult"));
            functions.implicitWait(1000);
            functions.isDisplayed(By.xpath("//div[@role='tooltip' and text()='Result interpretation is correct.']"));
            functions.implicitWait(1000);
            functions.click(By.name("downresult"));
            functions.implicitWait(1000);
            functions.isDisplayed(By.xpath("//div[@role='tooltip' and text()='Result interpretation is lower than average, please refer to your doctor.']"));
            functions.implicitWait(1000);
            functions.click(By.name("upresult"));
            functions.implicitWait(1000);
            functions.isDisplayed(By.xpath("//div[@role='tooltip' and text()='Result interpretation is higher than average, please refer to your doctor.']"));
            functions.implicitWait(1000);
            functions.click(By.id("backbtn"));
            functions.assertText(By.id("listtype"), "Liste de vos résultats :");
            functions.implicitWait(2500);

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void studies() throws Exception {
        try {
            functions.refresh();
            functions.click(By.id("studies"));
            functions.implicitWait(2500);
            functions.assertText(By.id("listtype"),"Liste de vos études :");
            functions.assertText(By.xpath("//button[@id='45KSTRZ']/following::i"),"Not Completed");
            functions.click(By.id("45KSTRZ"));
            functions.implicitWait(2000);
            functions.assertText(By.id("details"),"Nom : Etudes du paracetamol\nDescription : Etudes sur les effets du paracetamol durant les 2 premières semaines de traitement");

            //Test with missing checkbox
            functions.click(By.xpath("//div[@id='question1']/descendant::input[1]"));
            functions.click(By.xpath("//div[@id='question3']/descendant::input[1]"));
            functions.click(By.xpath("//div[@id='question4']/descendant::input"));
            functions.click(By.id("confirm"));
            functions.click(By.id("submitform"));
            functions.assertText(By.id("errormsg"),"Please answer to every questions.");

            //Test without checkbox
            functions.click(By.xpath("//div[@id='question2']/descendant::input[1]"));
            functions.click(By.id("confirm"));
            functions.click(By.id("submitform"));
            functions.assertAlertCheckbox("Veuillez cocher la checkbox afin de soumettre votre autorisation");

            //Good test
            functions.click(By.id("confirm"));
            functions.click(By.id("submitform"));
            functions.assertAlertCheckbox("Formulaire envoyé. Vous allez maintenant être redirigé vers la page d'accueil.");
            functions.implicitWait(1500);
            functions.assertText(By.id("welcome"), "Welcome M. Delaye");

            functions.click(By.id("studies"));
            functions.implicitWait(2500);
            functions.assertText(By.id("listtype"),"Liste de vos études :");
            functions.assertText(By.xpath("//button[@id='45KSTRZ']/following::i"),"Completed");


        } catch (Exception e){
            System.out.println(e);
        }
    }

    @AfterAll
    public static void disconnectTest() throws Exception {
        try {
            functions.refresh();
            functions.click(By.id("disconnect"));
            functions.implicitWait(1500);
            functions.assertText(By.id("confirmmsg"),"Are you sure?");
            functions.click(By.id("no"));
            functions.implicitWait(1500);
            functions.assertText(By.id("welcome"), "Welcome M. Delaye");
            functions.click(By.id("disconnect"));
            functions.implicitWait(1500);
            functions.assertText(By.id("confirmmsg"),"Are you sure?");
            functions.click(By.id("yes"));
            functions.isDisplayed(By.id("loginsubmit"));

            functions.quit();
        } catch (Exception e){
            System.out.println(e);
        }
    }

}



