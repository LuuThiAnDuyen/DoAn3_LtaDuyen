package com.anduyen.stepdefinitions;

import com.anduyen.common.BaseTest;
import com.anduyen.constants.ConstantGlobal;
import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.Given;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class Steps_LoginToAddCart {
    @Given("user logs in with valid credentials")
    public void userLogsInWithValidCredentials() throws InterruptedException {
        //BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConstantGlobal.URL);
        driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();

        driver.findElement(By.xpath("//input[@id='login-form_username']")).sendKeys("anduyen2");
        driver.findElement(By.xpath("//input[@id='login-form_password']")).sendKeys("anduyen2");

        DriverManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        DriverManager.getDriver().findElement(By.xpath("//div[@class='ant-notification-notice ant-notification-notice-success ant-notification-notice-closable']")).isDisplayed();
        System.out.println("ok");
        Thread.sleep(2000);
        //DriverManager.quit();
    }
}
