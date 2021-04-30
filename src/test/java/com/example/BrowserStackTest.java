package com.example;

import io.percy.selenium.*;
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
        String url = "https://ci.bsstag.com";
        driver.get(url);
        takePercySnapshot("Home page");
        driver.findElement(By.cssSelector("a[title=Pricing]")).click();
        takePercySnapshot("Pricing page");
        actions.moveToElement(driver.findElement(By.id("product-menu-toggle"))).perform();
        driver.findElement(By.cssSelector("a[data-product=Automate]")).click();
        driver.findElement(By.xpath("//span[text()='Integrations']")).click();
        takePercySnapshot("Integrations Automate page");
        actions.moveToElement(driver.findElement(By.id("developers-menu-toggle"))).perform();
        driver.findElement(By.cssSelector("a[title=Documentation]")).click();
        takePercySnapshot("Documentation page");
    }

    private void takePercySnapshot(String name) {
        System.out.println(driver.getCurrentUrl());
        percy.snapshot(name, Arrays.asList(375, 480, 720, 1280, 1440, 1920));
    }

}
