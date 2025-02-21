package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.fetch.Fetch;
import org.openqa.selenium.devtools.v131.network.model.Request;

import java.util.Optional;

public class NetworkMocking {
    public static void main(String[] args) throws InterruptedException {
        ChromeDriver driver = new ChromeDriver();

        DevTools devtools = driver.getDevTools();
        devtools.createSession();

        devtools.send(Fetch.enable(Optional.empty(),Optional.empty()));

        devtools.addListener(Fetch.requestPaused(), request ->
                {
                    Request req = request.getRequest();
                    if(req.getUrl().contains("shetty")){
                        String newUrl = request.getRequest().getUrl().replace("=shetty","=BadGuy");
                        System.out.println(newUrl);
                        devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(newUrl), Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
                    }else{
                        devtools.send(Fetch.continueRequest(request.getRequestId(), Optional.of(req.getUrl()), Optional.of(req.getMethod()), Optional.empty(), Optional.empty(), Optional.empty()));
                    }
                }
                );
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();

        Thread.sleep(2000);

        int size = driver.findElements(By.cssSelector("tr")).size();

        System.out.println("Total records found " + (size - 1));
        driver.quit();
    }
}
