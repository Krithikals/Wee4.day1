package week4.day1;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WindowHandleAss {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.irctc.co.in/nget/train-search");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[text()='OK']")).click();
		Thread.sleep(2000);
		driver.findElement(By.partialLinkText("FLIGHTS")).click();
		//driver.findElement(By.xpath("//label[text()='FLIGHTS']")).click();
		Set<String> windowHandleSet = driver.getWindowHandles();
		List<String> windowHandleList = new ArrayList<String>(windowHandleSet);
		driver.switchTo().window(windowHandleList.get(1));
		String text = driver.findElement(By.xpath(" //a[text()=' flights@irctc.co.in']")).getText();
		System.out.println(driver.getTitle());
		System.out.println(text);
		
	}

}
