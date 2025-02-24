package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Connection;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.network.model.ConnectionType;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Optional;

public class NetworkSpeed {
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.send(Network.emulateNetworkConditions(true,3000,10000,10000, Optional.of(ConnectionType.CELLULAR2G),Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.addListener(Network.loadingFailed(),loadingFailed ->
        {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());

            // Define the desired date and time format
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            // Convert Timestamp to Date
            String formattedDate = sdf.format(timestamp);

            // Output the formatted date
            String errorMessage = loadingFailed.getErrorText();

            System.out.println(formattedDate + " with " + errorMessage);
        });
        Long startTime = System.currentTimeMillis();
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
        Long endTime = System.currentTimeMillis();

        System.out.println(endTime - startTime);
        driver.quit();

    }
}
