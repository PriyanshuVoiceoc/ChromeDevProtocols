package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.HashMap;
import java.util.Map;

public class GeoLocationChange {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();
        driver.manage().deleteAllCookies();

        Map<String,Object> param = new HashMap<>();
        param.put("latitude",40);
        param.put("longitude",3);
        param.put("accuracy",1);

        driver.executeCdpCommand("Emulation.setGeolocationOverride",param);

        driver.get("https://www.google.com");

        driver.findElement(By.name("q")).sendKeys("Netflix", Keys.ENTER);

    }
}
