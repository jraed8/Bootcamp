package junit_bootcamp;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class ExpediaJunit {

	WebDriver driver;

	@BeforeClass
	public void AopenBrowser() throws Exception {

		System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.expedia.com/");
		driver.findElement(By.id("tab-flight-tab-hp")).click();
		Thread.sleep(3000);
		driver.findElement(By.id("flight-type-roundtrip-label-hp-flight")).click();
	}

	@After
	public void closeBrowser() throws Exception {
		Thread.sleep(3000);
		driver.close();
		driver.quit();
	}

	@Test
	public void searchCityFromTo() throws Exception {
		driver.findElement(By.id("flight-origin-hp-flight")).click();
		driver.findElement(By.id("flight-origin-hp-flight")).sendKeys("phl");
		Thread.sleep(3000);
		driver.findElement(By.id("flight-destination-hp-flight")).click();
		driver.findElement(By.id("flight-destination-hp-flight")).sendKeys("sfo");
		Thread.sleep(3000);
		driver.findElement(By.id("flight-departing-hp-flight")).click();
		Thread.sleep(3000);
	}

	@Test
	public void searchDepartingDate() throws Exception {

		String exp_month_dep = "Aug 2019";
		String exp_day_dep = "28";
		String act_header1 = driver.findElement(By.xpath("//div[@class='datepicker-cal']/div[2]/table/caption"))
				.getText();
		String act_header2 = driver.findElement(By.xpath("//div[@class='datepicker-cal']/div[3]/table/caption"))
				.getText();

		while (!(act_header1.equalsIgnoreCase(exp_month_dep) || act_header2.equalsIgnoreCase(exp_month_dep))) {
			driver.findElement(
					By.xpath("//button[@class='datepicker-paging datepicker-next btn-paging btn-secondary next']"))
					.click();
			act_header1 = driver.findElement(By.xpath("//div[@class='datepicker-cal']/div[2]/table/caption")).getText();
			act_header2 = driver.findElement(By.xpath("//div[@class='datepicker-cal']/div[3]/table/caption")).getText();

		}

		if (act_header1.equalsIgnoreCase(exp_month_dep)) {
			List<WebElement> dates = driver.findElements(By.xpath(
					"//div[@class='datepicker-cal']/div[2]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"));
			int count = dates.size();
			for (int i = 0; i < count; i++) {
				String date = driver.findElements(By.xpath(
						"//div[@class='datepicker-cal']/div[2]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"))
						.get(i).getAttribute("data-day");
				if (date.contains(exp_day_dep)) {
					driver.findElement(By.xpath(
							"//div[@class='datepicker-cal']/div[2]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button[@data-day='"
									+ exp_day_dep + "']"))
							.click();
					break;
				}
			}
		} else if (act_header2.equalsIgnoreCase(exp_month_dep)) {
			List<WebElement> dates = driver.findElements(By.xpath(
					"//div[@class='datepicker-cal']/div[3]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"));
			int count = dates.size();
			for (int i = 0; i < count; i++) {
				String date = driver.findElements(By.xpath(
						"//div[@class='datepicker-cal']/div[3]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"))
						.get(i).getAttribute("data-day");
				if (date.contains(exp_day_dep)) {
					driver.findElement(By.xpath(
							"//div[@class='datepicker-cal']/div[3]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button[@data-day='"
									+ exp_day_dep + "']"))
							.click();
					break;
				}
			}
		}

		Thread.sleep(3000);
	}

	@Test
	public void searchReturningDate() {
		driver.findElement(By.id("flight-returning-hp-flight")).click();
		String retMo1 = driver
				.findElement(By.xpath("//*[@id='flight-returning-wrapper-hp-flight']/div/div/div[2]/table/caption"))
				.getText();
		String retMo2 = driver
				.findElement(By.xpath("//*[@id='flight-returning-wrapper-hp-flight']/div/div/div[3]/table/caption"))
				.getText();

		String exp_moyr_ret = "Jan 2020";
		String exp_day_ret = "22";

		while (!(retMo1.equalsIgnoreCase(exp_moyr_ret) || retMo2.equalsIgnoreCase(exp_moyr_ret))) {
			driver.findElement(By.xpath("//*[@id='flight-returning-wrapper-hp-flight']/div/div/button[2]")).click();
			retMo1 = driver.findElement(By.xpath("//div[@class='datepicker-cal']/div[2]/table/caption")).getText();
			retMo2 = driver.findElement(By.xpath("//div[@class='datepicker-cal']/div[3]/table/caption")).getText();
		}
		if (retMo1.equalsIgnoreCase(exp_moyr_ret)) {
			List<WebElement> dates = driver.findElements(By.xpath(
					"//*[@id='flight-returning-wrapper-hp-flight']/div/div/div[2]/table/tbody/tr/td/button[@class='datepicker-cal-date']"));
			int count = dates.size();
			for (int i = 0; i < count; i++) {
				String date = driver.findElements(By.xpath(
						"//div[@class='datepicker-cal']/div[2]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"))
						.get(i).getAttribute("data-day");
				if (date.contains(exp_day_ret)) {
					driver.findElement(By.xpath(
							"//div[@class='datepicker-cal']/div[2]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button[@data-day='"
									+ exp_day_ret + "']"))
							.click();
					break;
				}
			}
		} else if (retMo2.equalsIgnoreCase(exp_moyr_ret)) {
			List<WebElement> dates = driver.findElements(By.xpath(
					"//div[@class='datepicker-cal']/div[3]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"));
			int count = dates.size();
			for (int i = 0; i < count; i++) {
				String date = driver.findElements(By.xpath(
						"//div[@class='datepicker-cal']/div[3]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button"))
						.get(i).getAttribute("data-day");
				if (date.contains(exp_day_ret)) {
					driver.findElement(By.xpath(
							"//div[@class='datepicker-cal']/div[3]/table/tbody/tr/td[@class='datepicker-day-number notranslate']/button[@data-day='"
									+ exp_day_ret + "']"))
							.click();
					break;
				}
			}
		}
	}

	@Test
	public void travelers() throws Exception {
		Thread.sleep(3000);
		driver.findElement(By.xpath("//button[@class='trigger-utility menu-trigger btn-utility btn-secondary dropdown-toggle theme-standard pin-left menu-arrow gcw-component gcw-traveler-amount-select gcw-component-initialized']")).click();

		int adultval = 3;
		int childval = 0;
		int infval = 0;

		String actadultval = driver.findElement(By.xpath("(//span[@class='uitk-step-input-value'])[1]")).getText();
		if(actadultval.equalsIgnoreCase("1")) {
			adultval--;
		}
		for (int i = 0; i < adultval; i++) {
				driver.findElement(By.xpath("(//button[@class='uitk-step-input-button uitk-step-input-plus'])[1]")).click();	
			}
		for (int i = 0; i < childval; i++) {
				driver.findElement(By.xpath("(//button[@class='uitk-step-input-button uitk-step-input-plus'])[2]")).click();
			}
		for (int i = 0; i < infval; i++) {
				driver.findElement(By.xpath("(//button[@class='uitk-step-input-button uitk-step-input-plus'])[3]")).click();
			}
	}
	
	@Test
	public void searchButton() throws Exception {
		driver.findElement(By.xpath("//button[@class='btn-primary btn-action gcw-submit']")).click();
	}
}
