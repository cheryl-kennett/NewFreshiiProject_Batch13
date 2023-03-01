package testcases.newfreshii.signin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class FRS_TC_10 {
    ChromeDriver driver;

    @Test
    public void frs_tc_10() {

        //------Click the circle user icon located on the top left on the main menu--------------------
        driver.findElement(By.xpath("//button[@qaattr='userProfile']")).click();

        //------Select ‘Sign In/Sign Up'---------------------------------------------------------------
        driver.findElement(By.xpath("//button[@qaattr='signIn']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.findElement(By.xpath("//button[@class='SocialButton_googleBtn__rf4GV']")).click();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        Set<String> handles= driver.getWindowHandles();

        System.out.println("All window handles : " +handles.size() );
        Iterator<String> it= handles.iterator();
        String parent_id= (String) it.next();
        System.out.println("parent window: " +parent_id);
        String child_id= (String) it.next();
        System.out.println("child window: " +child_id);

        driver.switchTo().window(child_id);

        WebElement email= driver.findElement(By.xpath("//input[@type='email']"));


        email.sendKeys("qatest.cheryl@gmail.com");

        WebElement next= driver.findElement(By.xpath("//span[text()='Next']"));
        next.click();;

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        WebElement password= driver.findElement(By.xpath("//input[@type='password']"));

        password.sendKeys("Abcd@1234");

        WebElement next2= driver.findElement(By.xpath("//span[text()='Next']"));
        next2.click();

        driver.switchTo().window(parent_id);

          //WebElement userName = driver.findElement(By.xpath("//h1[contains(.,'Hello Cheryl')]"));
        //Assert.assertTrue(userName.isDisplayed());

        //------Enter valid email-id-------------------------------------------------------------------
        //driver.findElement(By.xpath("//input[@name='email']")).sendKeys("qatest.cheryl@gmail.com");

        //------Enter valid password-------------------------------------------------------------------
        //driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Abcd@1234");

        //------Click Sign In--------------------------------------------------------------------------
        //driver.findElement(By.xpath("//button[@qaattr='commonButtonsSignIn']")).click();
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //------Assertion------------------------------------------------------------------------------
        //WebElement userName = driver.findElement(By.xpath("//h1[contains(.,'Hello Cheryl Kennett')]"));
        //Assert.assertTrue(userName.isDisplayed());

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

        //------Assertion--------------------------------------------------------

        String expectedtitle = "Freshii";
        String actualtitle = driver.getTitle();
        System.out.println("Actual title is : " + actualtitle);
        Assert.assertEquals(actualtitle, expectedtitle, "Page title not as expected");

    }

    @AfterTest
    public void afterTest(){
        driver.quit();
    }
}
