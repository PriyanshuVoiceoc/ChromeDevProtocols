package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v131.network.Network;
import org.openqa.selenium.devtools.v131.network.model.Request;
import org.openqa.selenium.devtools.v131.network.model.Response;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class NetworkActivityLogs
{
    public static void main(String[] args) {
        ChromeDriver driver = new ChromeDriver();

//        Map<String,Object> param = new HashMap<>();
//        param.put("maxTotalBufferSize",1024);
//        param.put("maxResourceBufferSize",1024);
//        param.put("maxPostDataSize",1024);
//        driver.executeCdpCommand("Network.enable", param);
        DevTools devTools = driver.getDevTools();
        devTools.createSession();

        devTools.send(Network.enable(Optional.empty(),Optional.empty(),Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), request ->
        {
            Request req = request.getRequest();
//            System.out.println(req.getUrl());
//            req.getHeaders();
        });
        devTools.addListener(Network.responseReceived(), response ->
        {
            Response res = response.getResponse();
//            System.out.println(res.getUrl());
//            System.out.println(res.getStatus());
            if(res.getStatus().toString().startsWith("4")){
                System.out.println(res.getUrl() + " is failing with " + res.getStatus());
            }
        }
        );
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();

        driver.quit();
    }
}
