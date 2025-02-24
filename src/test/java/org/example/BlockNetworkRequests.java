package org.example;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;

import java.util.Optional;

public class BlockNetworkRequests {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*.jpg","*.css")));

        Long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo");

        driver.findElement(By.linkText("Browse Products")).click();
        driver.findElement(By.linkText("Selenium")).click();

        driver.findElement(By.cssSelector(".add-to-cart")).click();

        System.out.println(driver.findElement(By.cssSelector("p")).getText());
        Long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
        driver.quit();

    }
}
