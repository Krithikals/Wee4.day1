package week4.day1;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class FramesAssignment {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver= new ChromeDriver();
		driver.get("https://www.w3schools.com/js/tryit.asp?filename=tryjs_confirm");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		//WebElement frames = driver.findElement(By.xpath("//h2[text()='JavaScript Confirm Box']"));
		driver.switchTo().frame("iframeResult");
		driver.findElement(By.xpath("//button[text()='Try it']")).click();
		Alert alert = driver.switchTo().alert();
		alert.dismiss();
		String textSucc = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		if(textSucc.contains("Cancel"))
		{
			System.out.println("Test passed & user pressed Cancelled");
		}
		driver.switchTo().defaultContent();
	

	}

}
