package org.example;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v85.emulation.Emulation;

import java.util.Optional;

public class MobileEmulatorTest
{
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();

        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Emulation.setDeviceMetricsOverride(600,1000,50,true,Optional.empty() ,Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty(),Optional.empty()));

        driver.get("https://www.amazon.in/");
    }
}
