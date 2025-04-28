package com.anduyen.stepdefinitions;

import com.anduyen.common.BaseTest;
import com.anduyen.constants.ConstantGlobal;
import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Steps_Login {
    @Given("user navigate to login page")
    public void userNavigateToLoginPage() {
            BaseTest.createDriver("edge");
            WebDriver driver = DriverManager.getDriver();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.get(ConstantGlobal.URL);
            driver.findElement(By.xpath("//a[contains(text(),'Đăng nhập')]")).click();
    }

    @When("user enter username and password")
    public void userEnterUsernameAndPassword() {
        WebDriver driver = DriverManager.getDriver();
        driver.findElement(By.xpath("//input[@id='login-form_username']")).sendKeys("anduyen1");
        driver.findElement(By.xpath("//input[@id='login-form_password']")).sendKeys("anduyen1");

    }

    @And("click login button")
    public void clickLoginButton() {
        DriverManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();

    }

    @Then("user redirect to home page")
    public void userRedirectToHomePage() throws InterruptedException {
        DriverManager.getDriver().findElement(By.xpath("//div[@class='ant-notification-notice ant-notification-notice-success ant-notification-notice-closable']")).isDisplayed();
        System.out.println("ok");
        Thread.sleep(2000);
        DriverManager.quit();
    }


    @When("user enter username invalid {string} or password invalid {string} and click login button")
    public void userEnterUsernameInvalidStringOrPasswordInvalidStringAndClickLoginButton(String username, String password) {
        WebDriver driver = DriverManager.getDriver();
        driver.findElement(By.xpath("//input[@id='login-form_username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@id='login-form_password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("displays an error message and do not turn page")
    public void displaysAnErrorMessageAndDoNotTurnPage() throws InterruptedException {
        WebElement userName = DriverManager.getDriver().findElement(By.xpath("//input[@id='login-form_username']"));
        WebElement passWord = DriverManager.getDriver().findElement(By.xpath("//input[@id='login-form_password']"));
        String usernameValue = userName.getAttribute("value");
        String passwordValue = passWord.getAttribute("value");


        List<WebElement> notifications = DriverManager.getDriver().findElements(By.xpath("//div[@role='alert']"));
        System.out.println(notifications);
        boolean errorMessageFound = false;

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));

        try {

            WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@role='alert']")
            ));


            String notificationText = notification.getText();
            System.out.println("Thông báo lỗi: " + notificationText);


            if (notificationText.contains("An error occurred while logging in.")) {
                System.out.println("Thông báo lỗi chính xác!");
            } else {
                System.out.println("Nội dung thông báo không khớp mong đợi.");
            }

        } catch (Exception e) {
            System.out.println("Không tìm thấy thông báo lỗi.");
        }


        if (usernameValue.isEmpty()) {
            try {
                WebElement usernameError = DriverManager.getDriver().findElement(By.cssSelector("div[id='login-form_username_help'] div[class='ant-form-item-explain-error']"));
                assert usernameError.isDisplayed();
                System.out.println("Lỗi hiển thị dưới ô username hợp lệ.");
            } catch (Exception e) {
                System.out.println("Không tìm thấy lỗi dưới ô username.");
            }
        }


        if (passwordValue.isEmpty()) {
            try {
                WebElement passwordError = DriverManager.getDriver().findElement(By.cssSelector("div[id='login-form_password_help'] div[class='ant-form-item-explain-error']"));
                assert passwordError.isDisplayed();
                System.out.println("Lỗi hiển thị dưới ô password hợp lệ.");
            } catch (Exception e) {
                System.out.println("Không tìm thấy lỗi dưới ô password.");
            }
        }
        Thread.sleep(2000);
        DriverManager.quit();
    }
}



