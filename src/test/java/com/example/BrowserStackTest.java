package com.example;

import io.percy.selenium.Percy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

public class BrowserStackTest {

    private WebDriver driver;
    private Percy percy;

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/sujaysawant/Documents/Binary/chromedriver");
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        percy = new Percy(driver);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }

    @Test
    public void test() {
        Actions actions = new Actions(driver);
        String url = "https://www.browserstack.com";
//        String url = "https://k8s.bsstag.com";
        driver.get(url);
        percy.snapshot("Home page", Arrays.asList(480, 1280, 1920));
        driver.findElement(By.cssSelector("a[title=Pricing]")).click();
        percy.snapshot("Pricing page", Arrays.asList(480, 1280, 1920));
        actions.moveToElement(driver.findElement(By.id("product-menu-toggle"))).perform();
        driver.findElement(By.cssSelector("a[data-product=Automate]")).click();
        driver.findElement(By.xpath("//span[text()='Integrations']")).click();
        percy.snapshot("Integrations Automate page", Arrays.asList(480, 1280, 1920));
        actions.moveToElement(driver.findElement(By.id("developers-menu-toggle"))).perform();
        driver.findElement(By.cssSelector("a[title=Documentation]")).click();
        percy.snapshot("Documentation page", Arrays.asList(480, 1280, 1920));
    }

}
