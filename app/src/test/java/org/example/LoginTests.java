package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTests {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");

    }

    @Test(description = "This test validates error message when credentials are incorrect", priority = 0)
    public void verifyIncorrectCredentials() {

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123$$");
        driver.findElement(By.xpath("//*[@class='oxd-form']/div[3]/button")).submit();

        String actualErrorMessage = driver.findElement(By.xpath("//*[@class='orangehrm-login-error']/div/div/p")).getText();

        // Verify Error Message
        Assert.assertEquals(actualErrorMessage,"Invalid credentials");

    }

    @Test(description = "This test will fail", priority = 1)
    public void verifyBlankCredentials() {

        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("admin123$$");
        driver.findElement(By.xpath("//*[@class='oxd-form']/div[3]/button")).submit();

        String actualErrorMessage = driver.findElement(By.xpath("//*[@class='oxd-form-row']/div/span")).getText();

        // Verify Error Message
        Assert.assertEquals(actualErrorMessage,"Invalid credentials");

    }

    @Test(description = "This test validates  successful login to Home page", priority = 2)
    public void verifyLoginPage() {

        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.xpath("//*[@class='oxd-form']/div[3]/button")).submit();

        String homePageHeading = driver.findElement(By.xpath("//*[@class='oxd-topbar-header-breadcrumb']/h6")).getText();

        //Verify new page - HomePage
        Assert.assertEquals(homePageHeading,"Dashboard");

    }

    @AfterMethod
    public void tearDown() {

        driver.quit();
    }

}