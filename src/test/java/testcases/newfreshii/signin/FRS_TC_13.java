package testcases.newfreshii.signin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class FRS_TC_13 {
    ChromeDriver driver;

    @Test
    public void frs_tc_13() {

        //------Click the circle user icon located on the top left on the main menu--------------------
        driver.findElement(By.xpath("//button[@qaattr='userProfile']")).click();

        //------Select ‘Sign In/Sign Up'---------------------------------------------------------------
        driver.findElement(By.xpath("//button[@qaattr='signIn']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //------Enter invalid email-id-------------------------------------------------------------------
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("qatest.cheryl@gmail.co");

        //------Enter valid password-------------------------------------------------------------------
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Abcd@1234");

        //------Click Sign In--------------------------------------------------------------------------
        driver.findElement(By.xpath("//button[@qaattr='commonButtonsSignIn']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //------Assertion------------------------------------------------------------------------------
        WebElement loginError = driver.findElement(By.xpath("//p[contains(.,'Please, enter a valid password.')]"));
        Assert.assertTrue(loginError.isDisplayed());

    }

    @BeforeTest
    public void beforeTest() {

        System.setProperty("webdriver.chrome.driver", "C:\\MyJavaWorkspace\\NewFreshiiProject_Batch13\\drivers\\chromedriver.exe");
        System.setProperty("webdriver.gecko.driver", "C:\\MyJavaWorkspace\\NewFreshiiProject_Batch13\\drivers\\geckodriver.exe");

        driver = new ChromeDriver();

        //------Navigate to Freshii website landing page-------------------------------------------------
        driver.get("https://web-uat.freshii.com/en");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //------Enter valid Canadian postal code when prompted-------------------------------------------
        driver.findElement(By.name("postalCode")).sendKeys("L6V 4L2");
        driver.findElement(By.xpath("//button[contains(.,'FIND STORE')]")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    @AfterTest
    public void afterTest(){
        driver.quit();

    }

}
