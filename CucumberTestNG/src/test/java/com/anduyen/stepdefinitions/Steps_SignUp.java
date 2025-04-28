package com.anduyen.stepdefinitions;

import com.anduyen.common.BaseTest;
import com.anduyen.constants.ConstantGlobal;
import com.anduyen.driver.DriverManager;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Steps_SignUp {

    @Given("user navigates to Sign up page")
    public void userNavigatesToSignUpPage() {
        BaseTest.createDriver("edge");
        WebDriver driver = DriverManager.getDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(ConstantGlobal.URL);
        driver.findElement(By.xpath("//a[contains(text(),'Đăng ký')]")).click();
        driver.findElement(By.xpath("//h1[contains(text(),'Đăng ký tài khoản')]")).isDisplayed();
        System.out.println("Directed sign up page.");

    }
    @When("user enters complete and valid information into the required fields")
    public void userEntersCompleteAndValidInformationIntoTheRequiredFields() {
        WebDriver driver = DriverManager.getDriver();
        driver.findElement(By.xpath("//input[@id='register-form_taikhoan']")).sendKeys("anduyen3");
        driver.findElement(By.xpath("//input[@id='register-form_hoten']")).sendKeys("Lưu Thị An Duyên");
        driver.findElement(By.xpath("//input[@id='register-form_sodienthoai']")).sendKeys("0125457893");
        driver.findElement(By.xpath("//input[@id='register-form_email']")).sendKeys("anduyen2@gmail.com");
        driver.findElement(By.xpath("//input[@id='register-form_matkhau']")).sendKeys("anduyen3");
        driver.findElement(By.xpath("//input[@id='register-form_matkhau2']")).sendKeys("anduyen3");
        driver.findElement(By.xpath("//input[@id='register-form_checkrule']")).click();
    }

    @And("click sign up button")
    public void clickSignUpButton() {
        DriverManager.getDriver().findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("system notice sign up success and direct to login page")
    public void systemNoticeSignUpSuccessAndDirectToLoginPage() throws InterruptedException {
        DriverManager.getDriver().findElement(By.xpath("//div[@class='ant-notification-notice ant-notification-notice-success ant-notification-notice-closable']")).isDisplayed();
        System.out.println("Thông báo hiển thị");
        DriverManager.getDriver().findElement(By.xpath("//h2[normalize-space()='Login Skin Care']")).isDisplayed();
        Thread.sleep(2000);
        DriverManager.quit();
    }
    @When("user left one of the information fields blank {string} or {string} or {string} or {string} or {string} or {string}")
    public void userLeftOneOfTheInformationFieldsBlankOrOrOrOrOrOr(String arg0, String arg1, String arg2, String arg3, String arg4, String arg5) {
        WebDriver driver = DriverManager.getDriver();
        driver.findElement(By.xpath("//input[@id='register-form_taikhoan']")).sendKeys(arg0);
        driver.findElement(By.xpath("//input[@id='register-form_hoten']")).sendKeys(arg1);
        driver.findElement(By.xpath("//input[@id='register-form_sodienthoai']")).sendKeys(arg2);
        driver.findElement(By.xpath("//input[@id='register-form_email']")).sendKeys(arg3);
        driver.findElement(By.xpath("//input[@id='register-form_matkhau']")).sendKeys(arg4);
        driver.findElement(By.xpath("//input[@id='register-form_matkhau2']")).sendKeys(arg5);
        driver.findElement(By.xpath("//input[@id='register-form_checkrule']")).click();
    }
    @Then("system notice error message and do not turn page")
    public void systemNoticeErrorMessageAndDoNotTurnPage() {
        WebDriver driver = DriverManager.getDriver();
        boolean hasError = false;
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        String[][] fields = {
                {"register-form_taikhoan", "Please input your username!"},
                {"register-form_hoten", "Please input your full name!"},
                {"register-form_sodienthoai", "Please input your phone number!"},
                {"register-form_email", "Please input your email!"},
                {"register-form_matkhau", "Please input your password!"},
                {"register-form_matkhau2", "Please input your password again!"}
        };

        for (String[] field : fields) {
            WebElement inputField = driver.findElement(By.id(field[0]));
            if (inputField.getAttribute("value").isEmpty()) {
                try {

                    WebElement errorMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                            By.xpath("//div[@class='ant-form-item-explain-error']")
                    ));


                    String actualErrorText = errorMsg.getText().trim();
                    System.out.println("Thông báo lỗi thực tế: " + actualErrorText);

                    if (actualErrorText.equals(field[1])) {
                        System.out.println("Thông báo lỗi đúng: " + field[1]);
                        hasError = true;
                    } else {
                        System.out.println("⚠ Lỗi hiển thị không khớp mong đợi: " + actualErrorText);
                    }

                } catch (TimeoutException e) {
                    System.out.println("Không tìm thấy thông báo lỗi cho: " + field[0]);
                }
            }
        }

        if (hasError) {
            System.out.println("Có lỗi xảy ra, không chuyển trang.");
        }

        driver.quit();
    }


}
