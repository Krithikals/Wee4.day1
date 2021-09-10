package week4.day1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ServiceNoe {

	public static void main(String[] args) throws InterruptedException, IOException {

     WebDriverManager.chromedriver().setup();
     ChromeDriver driver= new ChromeDriver();
     driver.get("https://dev113545.service-now.com/");
     driver.manage().window().maximize();
     driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
     driver.switchTo().frame(0);
     driver.findElement(By.id("user_name")).sendKeys("admin");
     driver.findElement(By.id("user_password")).sendKeys("w6hnF2FRhwLC");
     driver.findElement(By.id("sysverb_login")).click();
     driver.findElement(By.id("filter")).sendKeys("incident",Keys.ENTER);
     driver.findElement(By.xpath("(//div[text()='All'])[2]")).click();
     Thread.sleep(2000);
     WebElement frame2 = driver.findElement(By.xpath("//iframe[@id='gsft_main']"));
     driver.switchTo().frame(frame2);
     driver.findElement(By.xpath("//button[@type='submit']")).click();
     String incidentNum = driver.findElement(By.id("incident.number")).getAttribute("value");
     System.out.println(incidentNum);
     driver.findElement(By.xpath("//span[@class='icon icon-search']")).click();
     Set<String> windowHandle = driver.getWindowHandles();
     List<String> windowHandleList = new ArrayList<String>(windowHandle);
     driver.switchTo().window(windowHandleList.get(1));
     System.out.println(driver.getTitle());
     driver.manage().window().maximize();
     Thread.sleep(3000);
     driver.findElement(By.xpath(" ((//tbody[@class=\"list2_body\"]//tr)[1]/td)[3]/a")).click();
     driver.switchTo().window(windowHandleList.get(0));
     driver.switchTo().frame(frame2);
     driver.findElement(By.id("incident.short_description")).sendKeys("Hello");
     driver.findElement(By.id("sysverb_insert_bottom")).click();
     driver.findElement(By.xpath("//div[@class=\"input-group\"]/input")).sendKeys(incidentNum,Keys.ENTER);
     WebElement search = driver.findElement(By.xpath("(//tbody[@class='list2_body']//td)[3]/a"));
     String text = search.getText();
     System.out.println(text);
     search.click();
     if(text.equals(incidentNum))
     {
    	 System.out.println("Test Passed");
     }
     else
     {
    	 System.out.println("Test failed");
     }
	

	File src= driver.getScreenshotAs(OutputType.FILE);
	File dts= new File("./snaps/screenshot.png");
	FileUtils.copyFile(src, dts);
	
	}
}
