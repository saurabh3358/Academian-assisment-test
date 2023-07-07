package AutomateBookmyshow;


	import java.time.Duration;
	import java.util.ArrayList;
	import java.util.Iterator;
	import java.util.List;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;

	public class Boomyshowlogin {

		public static void main(String[] args) throws InterruptedException {
			System.setProperty("webdriver.chrome.driver", "G:\\\\\\\\Software testing\\\\\\\\Selinium Soft\\\\\\\\chromedriver_win32 (2)\\\\\\\\chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--remote-allow-origins=*");
			WebDriver driver = new ChromeDriver(options);

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

			// open bookmyshow
			driver.get("https://in.bookmyshow.com/explore/home/");

			wait.until(ExpectedConditions.elementToBeClickable((By.xpath("(//div[@class='sc-jQMNup jIFQaK'])[3]"))));

			// select city
			driver.findElement(By.xpath("(//div[@class='sc-jQMNup jIFQaK'])[3]")).click();

			// click on sign up
			WebElement singUp = driver.findElement(By.xpath("//div[@class='sc-iGPElx jsSlsO']"));
			wait.until(ExpectedConditions.elementToBeClickable(singUp));
			singUp.click();

			// click on continue with mail
			driver.findElement(By.xpath("(//div[@class='sc-RefOD iCukgs'])[2]")).click();

			// give input of mail
			driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("selauto@yopmail.com");

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='sc-exAgwC dmTBNW']")));
			WebElement ContinueButton = driver.findElement(By.xpath("//button[@class='sc-exAgwC dmTBNW']"));

			ContinueButton.click();

			((ChromeDriver) driver).executeScript("window.open()");
			// Switch to the new tab
			driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());

			// wait.until(ExpectedConditions.urlToBe("http://www.yopmail.com"));
			// Open the second tab
			driver.get("http://www.yopmail.com");

			// gie=ve input mail
			driver.findElement(By.xpath("//input[@name='login']")).sendKeys("selauto@yopmail.com");

			// to enter arrow
			driver.findElement(By.xpath("//i[@class='material-icons-outlined f36']")).click();

			// to refresh inbox
			// driver.findElement(By.xpath("//button[@id='refresh']")).click();
			driver.switchTo().frame("ifmail");

			// fetch opt
			WebElement otp = driver.findElement(By.xpath(
					"//*[@id='mail']/div/table/tbody/tr[1]/td/div/table/tbody/tr[4]/td/table/tbody/tr[2]/td/table/tbody/tr/td"));

			wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath(
					"//td[@style='text-decoration: none; color: rgba(51, 51, 51, 1); font-size: 24px; font-weight: 600']"))));
			String otpString = otp.getText();

			driver.switchTo().window(driver.getWindowHandles().stream().findFirst().orElseThrow());

			// System.out.println(otpString);
			Thread.sleep(2000);
			// seperate otp into single digit
			int i = 1;
			char[] seperate = otpString.toCharArray();

			String text = new String(seperate);

			System.out.println(text);

			for (int j = 0; j < seperate.length; j++) {
				List<WebElement> inputotpdigits = driver
						.findElements(By.xpath("//div[@class='sc-gZMcBi sc-jzgbtB grnTcO']/input"));
				System.out.println(seperate[j]);
				Iterator<WebElement> it = inputotpdigits.iterator();
				
				
			

				while (it.hasNext()) {
					WebElement ele = it.next();
					try {
						ele.sendKeys(text);
					} catch (Exception e)
					{

					}
					
				}
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class='sc-eLExRp ctptQL']")));
				
				 driver.findElement(By.xpath("//button[@class='sc-eLExRp ctptQL']")).click();
					 WebElement greetingElement = driver.findElement(By.xpath("//span[text()='Hi, Guest']"));
			        String greetingText = greetingElement.getText();
			        if (greetingText.equals("Hi, Guest")) {
			            System.out.println("User is successfully signed in.");
			        } else {
			            System.out.println("Sign in unsuccessful.");
			        }

			        // Close the browser
			        driver.quit();
				
				
			}
		}
	}


