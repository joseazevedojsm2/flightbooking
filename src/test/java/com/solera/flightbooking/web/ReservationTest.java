package com.solera.flightbooking.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ReservationTest {

    @Test
    void whenPassangerTryReservation_withCorrectData_shouldSucess() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");

        String oldUrl = driver.getCurrentUrl();
        Select select = new Select(driver.findElement(By.xpath("//body/div[@id='root']/section[1]/form[1]/div[1]/select[1]")));
        select.selectByVisibleText("Lisboa");

        select = new Select(driver.findElement(By.xpath("//body/div[@id='root']/section[1]/form[1]/div[2]/select[1]")));
        select.selectByVisibleText("Madrid");

        driver.findElement(By.xpath("//input[@id='checkin-date']"))
                .sendKeys("04112022");

        select = new Select(driver.findElement(By.xpath("//select[@id='room-selection']")));
        select.selectByVisibleText("between 2 and 9 years");


        driver.findElement(By.xpath("//input[@id='bags']")).click();

        select = new Select(driver.findElement(By.xpath("//select[@id='airline']")));
        select.selectByVisibleText("AirFrance");

        driver.findElement(By.xpath("//button[contains(text(),'Search Flights')]")).click();

        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section:nth-child(2) ul.FlightList_movies-list__zvl5d:nth-child(2) " +
                "li.Flight_movie__f8vIs a:nth-child(3) > button:nth-child(1)")));

        driver.findElement(By.cssSelector("section:nth-child(2) ul.FlightList_movies-list__zvl5d:nth-child(2) " +
                "li.Flight_movie__f8vIs a:nth-child(3) > button:nth-child(1)")).click();

        driver.get(driver.getCurrentUrl());
        oldUrl = driver.getCurrentUrl();

        driver.findElement(By.xpath("//input[@id='fname']"))
                .sendKeys("Test");

        driver.findElement(By.xpath("//input[@id='lname']"))
                .sendKeys("Test");

        driver.findElement(By.xpath("//input[@id='nacionality']"))
                .sendKeys("Test");

        driver.findElement(By.xpath("//input[@id='identification']"))
                .sendKeys("Test");

        select = new Select(driver.findElement(By.xpath("//select[@id='group']")));
        select.selectByVisibleText("between 2 and 9 years");

        driver.findElement(By.xpath("//input[@id='bags']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Buy')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'See Reservation')]")).click();



        wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));

        driver.get(driver.getCurrentUrl());

        assertNotEquals(":undefined",driver.findElement(By.cssSelector("div[id='root'] h3")).getText());
   }

    @Test
    void whenPassangerTryReservation_withWrongData_shouldFail() {
        System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Selenium\\ChromeDriver\\chromedriver_win32\\chromedriver.exe");

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://localhost:3000/");

        String oldUrl = driver.getCurrentUrl();
        Select select = new Select(driver.findElement(By.xpath("//body/div[@id='root']/section[1]/form[1]/div[1]/select[1]")));
        select.selectByVisibleText("Lisboa");

        select = new Select(driver.findElement(By.xpath("//body/div[@id='root']/section[1]/form[1]/div[2]/select[1]")));
        select.selectByVisibleText("Madrid");

        driver.findElement(By.xpath("//input[@id='checkin-date']"))
                .sendKeys("04112022");

        select = new Select(driver.findElement(By.xpath("//select[@id='room-selection']")));
        select.selectByVisibleText("between 2 and 9 years");


        driver.findElement(By.xpath("//input[@id='bags']")).click();

        select = new Select(driver.findElement(By.xpath("//select[@id='airline']")));
        select.selectByVisibleText("AirFrance");

        driver.findElement(By.xpath("//button[contains(text(),'Search Flights')]")).click();

        WebDriverWait wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("section:nth-child(2) ul.FlightList_movies-list__zvl5d:nth-child(2) " +
                "li.Flight_movie__f8vIs a:nth-child(3) > button:nth-child(1)")));

        driver.findElement(By.cssSelector("section:nth-child(2) ul.FlightList_movies-list__zvl5d:nth-child(2) " +
                "li.Flight_movie__f8vIs a:nth-child(3) > button:nth-child(1)")).click();

        driver.get(driver.getCurrentUrl());
        oldUrl = driver.getCurrentUrl();

        driver.findElement(By.xpath("//input[@id='nacionality']"))
                .sendKeys("Test");

        driver.findElement(By.xpath("//input[@id='identification']"))
                .sendKeys("Test");

        select = new Select(driver.findElement(By.xpath("//select[@id='group']")));
        select.selectByVisibleText("between 2 and 9 years");

        driver.findElement(By.xpath("//input[@id='bags']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Buy')]")).click();
        driver.findElement(By.xpath("//button[contains(text(),'See Reservation')]")).click();



        wait  = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(oldUrl)));

        driver.get(driver.getCurrentUrl());

        assertFalse(driver.getCurrentUrl().contains("undefined"));
    }
}
