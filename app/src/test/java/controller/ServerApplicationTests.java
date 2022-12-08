package controller;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import io.github.bonigarcia.wdm.WebDriverManager;


public class ServerApplicationTests {
    private WebDriver driver;
    ConfigurableApplicationContext webapp;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--no-sandbox");

        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver(options);

        webapp = SpringApplication.run(ServerApplication.class);

        driver.navigate().to("http://localhost:8080");

        driver.navigate().to("http://localhost:8080");
    }

    
    // Sceinario: User withdraws $200
    // User logs into the websites
    // User checks current balance
    // User withdraws $200
    // User checks balance 
    // User logs out
    // Sceinario: User withdraws $200
    // User logs into the websites
    // User checks current balance
    // User withdraws $200
    // User checks balance 
    // User logs out
    @Test
    public void T001_withdrawFunds() {
        boolean actual = false;

        driver.findElement(By.id("loginBtn")).click(); // Index to login page
        
        driver.findElement(By.id("manual")).click(); // Login to manual page

        driver.findElement(By.id("account")).sendKeys("12345");    // Enters credentials and logs in
        driver.findElement(By.id("pin")).sendKeys("54321");
        driver.findElement(By.id("login")).click();                               // Manual to menu page

        driver.findElement(By.id("balance")).click();       // Menu to Balance

        String avlStrBefore = driver.findElement(By.id("avaliable")).getText();   // Obtains the text in the record after the avaliable balance
        float balBefore = Float.parseFloat(avlStrBefore.replaceAll("[^\\d.]", ""));   // Formats string into a float

        driver.findElement(By.id("menu")).click();      // Balance to menu

        driver.findElement(By.id("withdraw")).click();  // Menu to withdraw

        driver.findElement(By.id("200")).click();   // If withdrawn works, it will return to the main menu

        driver.findElement(By.id("balance")).click();       // Menu to Balance

        String avlStrAfter = driver.findElement(By.id("avaliable")).getText();   // Obtains the text in the record after the avaliable balance
        float balAfter = Float.parseFloat(avlStrAfter.replaceAll("[^\\d.]", ""));   // Formats string into a float

        driver.findElement(By.id("menu")).click();      // Balance to menu

        driver.findElement(By.id("logout")).click();      // Logs out

        if(balBefore - 200 == balAfter)
            actual = true;

        Assert.assertTrue(actual);
    }

        // Sceinario: User withdraws $200
    // User logs into the websites
    // User checks current balance
    // User withdraws $200 6 times
    // User checks balance 
    // User logs out
    @Test
    public void T002_withdrawFundsMoreThanAvlaible() {
        boolean actual = false;

        driver.findElement(By.id("loginBtn")).click(); 
        
        driver.findElement(By.id("manual")).click(); 

        driver.findElement(By.id("account")).sendKeys("12345");    
        driver.findElement(By.id("pin")).sendKeys("54321");
        driver.findElement(By.id("login")).click();                               

        driver.findElement(By.id("balance")).click();       

        String avlStrBefore = driver.findElement(By.id("avaliable")).getText();   
        float balBefore = Float.parseFloat(avlStrBefore.replaceAll("[^\\d.]", ""));  

        driver.findElement(By.id("menu")).click(); 
        
        for(int i = 0; i < 6; i++){
            driver.findElement(By.id("withdraw")).click();  

            driver.findElement(By.id("200")).click();  
        }
        if(driver.findElement(By.id("transactionMessage")).isDisplayed())
            driver.findElement(By.id("menu")).click();

        driver.findElement(By.id("balance")).click();       

        String avlStrAfter = driver.findElement(By.id("avaliable")).getText();  
        float balAfter = Float.parseFloat(avlStrAfter.replaceAll("[^\\d.]", ""));   

        driver.findElement(By.id("menu")).click();    

        driver.findElement(By.id("logout")).click(); 

        if(balBefore - 600 == balAfter)
            actual = true;
        else if(balAfter == 0)
            actual = true;
        else if(balBefore - 600 < 0)
            actual = false;

        Assert.assertTrue(actual);
    }

            // Sceinario: User deposits $500
    // User logs into the websites
    // User checks current balance
    // User deposits $500
    // User checks total balance and avalibale balance
    // User logs out
            // Sceinario: User deposits $500
    // User logs into the websites
    // User checks current balance
    // User deposits $500
    // User checks total balance and avalibale balance
    // User logs out
    @Test
    public void T003_depositesFunds() {
        boolean actual = false;

        driver.findElement(By.id("loginBtn")).click(); 
        
        driver.findElement(By.id("manual")).click(); 

        driver.findElement(By.id("account")).sendKeys("12345");    
        driver.findElement(By.id("pin")).sendKeys("54321");
        driver.findElement(By.id("login")).click();                               

        driver.findElement(By.id("balance")).click();       

        String tolStrBefore = driver.findElement(By.id("total")).getText();   
        float balBefore = Float.parseFloat(tolStrBefore.replaceAll("[^\\d.]", ""));  

        driver.findElement(By.id("menu")).click(); 
 
        driver.findElement(By.id("deposit")).click();  

        driver.findElement(By.id("input")).sendKeys("500");
        driver.findElement(By.id("deposit")).click();  
        
        driver.findElement(By.id("balance")).click();       

        String tolStrAfter = driver.findElement(By.id("total")).getText();  
        float balAfter = Float.parseFloat(tolStrAfter.replaceAll("[^\\d.]", ""));   

        driver.findElement(By.id("menu")).click();    

        driver.findElement(By.id("logout")).click(); 

        if(balBefore + 500 == balAfter)
            actual = true;

        Assert.assertTrue(actual);

    }

    @After
    public void tearDown() {
        driver.close();
        driver.quit();
        webapp.close();
    }
}