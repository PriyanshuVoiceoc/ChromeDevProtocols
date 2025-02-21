package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class CDPCommandTest {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        Map parameter = new HashMap();
        parameter.put("width",600);
        parameter.put("height",1000);
        parameter.put("deviceScaleFactor",100);
        parameter.put("mobile",true);
        driver.executeCdpCommand("Emulation.setDeviceMetricsOverride",parameter);

        driver.get("https://www.unemployedonline.in/");

        driver.findElement(By.className("menu-icon")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("contact-link")));

        driver.findElement(By.className("contact-link")).click();

    }
}
